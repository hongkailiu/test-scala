package helpers

import messages.{MyMessage, MyMessageResponse, MyMessageRequestTwitt}
import akka.actor._
import play.Logger

/**
 * Created by ehongka on 5/25/15.
 */
trait TwitterHelper {
  def request(out: ActorRef, name: String, count: Int, resultHandler: ResultHandler)
}

class TwitterHelperImpl extends TwitterHelper {
  override def request(out: ActorRef, name: String, count: Int, resultHandler: ResultHandler): Unit = {
    TwitterHelper.request(out,name,count,resultHandler)
  }
}

object TwitterHelper extends TwitterHelper {
  override def request(out: ActorRef, name: String, count: Int, resultHandler: ResultHandler) = {
    val twitterService: TwitterService = new TwitterService(name, count, resultHandler)
    twitterService.query()
  }
}

class MyActor(twitterHelper:TwitterHelper) extends Actor {

  override def receive = {
    case MyMessageRequestTwitt(out, name, count, resultHandler) => {
      Logger.info(s"MyActor: MyMessageTwittRequest $out $name $count.")
      twitterHelper.request(out, name, count, resultHandler)
      sender ! MyMessageResponse(MyMessage.TWITT_DONE_MSG)
    }
    case MyMessageResponse(message) => {
      Logger.info(s"MyActor receive: MyMessageResponse: $message")
    }
    case _ => {
      Logger.info(s"MyActor receive: unknown message")
      sender ! MyMessageResponse(MyMessage.UNKNOWN_MSG)
    }
  }

  override def preStart() = {
    Logger.info("MyActor: preStart!")

  }

  override def postStop() = {
    Logger.info("MyActor: postStop!")
  }

}
