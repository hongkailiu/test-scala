package controllers

import messages.MyMessageRequestTwitt
import akka.actor.{Actor, ActorRef, Props}
import helpers._
import models.Twitt
import play.Logger
import play.api.Play.current
import play.api.mvc.{Action, Controller, WebSocket}
import twitter4j.{QueryResult, Status}

import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

class Twitter extends Controller {

  // http://localhost:9000/twitter/show/dwts
  def show(hashTag: String) = Action {
    Ok(views.html.twitter(hashTag))
  }

  def twitterWebSocket = WebSocket.acceptWithActor[String, String] { request => out => {
    // http://localhost:9000/twitter/api/ht?name=dwts
    Logger.info("request.uri: " + request.uri)
    val o: Option[String] = request.getQueryString("name")

    if (o != None && o.isDefined) {
      val name: String = o.get
      MyWebSocketActor.props(out, name)
    } else {
      Logger.info("abnormal uri: " + request.uri)
      throw new IllegalArgumentException("abnormal uri: " + request.uri)
    }
  }
  }

}

object MyWebSocketActor {
  val systemProvider = new MyWebSocketActorSupporterImpl
  def props(out: ActorRef, name: String) = Props(new MyWebSocketActor(out, name, systemProvider))
}

class MyWebSocketActor(out: ActorRef, name: String, myWebSocketActorSupporter: MyWebSocketActorSupporter) extends Actor {
  val actorRef: ActorRef = myWebSocketActorSupporter.getActorRef(context)
  val myHandler: MyHandler = myWebSocketActorSupporter.getMyHandler(out)
  val internal: Int = myWebSocketActorSupporter.getInterval
  myWebSocketActorSupporter.getAkkaSystem.scheduler.schedule(0 seconds, internal seconds, actorRef, MyMessageRequestTwitt(out, name, 3, myHandler))

  override def preStart() = {
    Logger.info("MyWebSocketActor: Connected!")
  }

  override def receive = {
    case msg: String => {
      Logger.info("MyWebSocketActor receive: " + msg)
      out ! msg
    }

  }

  override def postStop() = {
    Logger.info("MyWebSocketActor: Disconnected!")
  }

}

class MyHandler(out: ActorRef) extends ResultHandler {
  private var idSet: Set[String] = Set()

  override def handle(queryResult: QueryResult): Unit = {
    val statusList: java.util.List[Status] = queryResult.getTweets
    //println(list)
    if (null != statusList) {
      for (x <- statusList) handleStatus(x)
    }
  }

  private def handleStatus(status: Status): Unit = {
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
