package controllers

import Messages.MyMessageTwittRequest
import akka.actor.{Actor, ActorRef, Props}
import helper.{MyActor, ResultHandler}
import models.Twitt
import play.Logger
import play.api.Play.current
import play.api.mvc.{Action, Controller, WebSocket}
import twitter4j.{QueryResult, Status}

import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

object Twitter extends Controller {
  //val system = ActorSystem.create("testPlay")

  //http://localhost:9000/persons/all
  def index = Action {
    Ok(views.html.twitter("twitter is here"))
  }

  // http://localhost:9000/twitter/show/dwts
  def show(hashTag: String) = Action {
    Ok(views.html.twitter(hashTag))
  }

  def twitterWebSocket = WebSocket.acceptWithActor[String, String] { request => out => {
    // http://localhost:9000/twitter/api/ht?name=dwts
    Logger.info("request.uri: " + request.uri)
    //var name = "dummy"
    val o: Option[String] = request.getQueryString("name")

    if (o != None && o.isDefined) {
      val name: String = o.get
      MyWebSocketActor.props(out, name)
    } else {
      Logger.info("abnormal uri: " + request.uri)
      //Props.empty
      throw new IllegalArgumentException("abnormal uri: " + request.uri)
    }
    //val name:String = request.getQueryString("name").getOrElse(0)
    //val uri:String = request.uri
    //val name:String = uri.substring(uri.lastIndexOf('/')+1)
    //Logger.info("name: " + name)
    //val name = request.
    //MyWebSocketActor.props(out, TwitterHelper.dwts)
    //MyWebSocketActor.props(out, "dwts")
  }


  }

}

object MyWebSocketActor {
  def props(out: ActorRef, name: String) = Props(new MyWebSocketActor(out, name))
}

class MyWebSocketActor(out: ActorRef, name: String) extends Actor {
  //import MyWebSocketActor._

  val actorRef: ActorRef = context.actorOf(Props[MyActor], "myActor")
  //play.libs.Akka.system.scheduler.schedule(new FiniteDuration(0, SECONDS), new FiniteDuration(10, SECONDS), actorRef, MyMessageTwittRequest(out, name, 3, new MyHandler(out)))
  play.libs.Akka.system.scheduler.schedule(0 seconds, 10 seconds, actorRef, MyMessageTwittRequest(out, name, 3, new MyHandler(out)))

  override def preStart() = {
    Logger.info("MyWebSocketActor: Connected!")
    //TwitterHelper.myActorRef ! MyMessagePlus(out, name)
  }

  //private[this] var (channel) = Concurrent.broadcast[String]
  override def receive = {
    case msg: String => {
      Logger.info("MyWebSocketActor receive: " + msg)
      out ! msg
    }

  }

  override def postStop() = {
    Logger.info("MyWebSocketActor: Disconnected!")
    //TwitterHelper.myActorRef ! MyMessageMinus(out, name)
  }

}

class MyHandler(out: ActorRef) extends ResultHandler {
  var idSet: Set[String] = Set()

  override def handle(queryResult: QueryResult): Unit = {
    val statusList: java.util.List[Status] = queryResult.getTweets
    //println(list)
    if (null != statusList) {
      for (x <- statusList) handleStatus(x)
    }
  }

  def handleStatus(status: Status): Unit = {
    /*println("=====a=======")
    println(status.getCreatedAt)
    println(status.getId)
    println(status.getText)
    println("=====b=======")*/
    val twitt = new Twitt(status.getCreatedAt.toString, java.lang.Long.toString(status.getId), status.getText)

    if (!idSet.contains(twitt.id)) {
      val msg = twitt.toString
      Logger.info(s"MyHandler: send to $out $msg.")
      out ! msg
      idSet = idSet + twitt.id
      Logger.info(s"MyHandler: idSet $idSet.")
    } else {
      Logger.info("MyHandler: already sent: " + twitt.toString)
    }
  }
}
