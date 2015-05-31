package helper

import Messages.{MyMessage, MyMessageResponse, MyMessageTwittRequest}
import akka.actor._
import play.Logger

/**
 * Created by ehongka on 5/25/15.
 */
object TwitterHelper {
  def request(out: ActorRef, name: String, count: Int, resultHandler: ResultHandler) = {
    val twitterService: TwitterService = new TwitterService(name, count, resultHandler)
    twitterService.query()
  }
}

class MyActor extends Actor {

  override def receive = {
    case MyMessageTwittRequest(out: ActorRef, name: String, count: Int, resultHandler: ResultHandler) => {
      Logger.info(s"MyActor: MyMessageTwittRequest $out $name $count.")
      TwitterHelper.request(out, name, count, resultHandler)
    }
    case a: Any => {
      Logger.info("MyActor receive: unknown message")
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
