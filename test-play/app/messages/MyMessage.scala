package messages

import akka.actor.ActorRef
import helpers.ResultHandler
import models.Twitt

/**
 * Created by hongkailiu on 2015-05-25.
 */

case class MyMessageRequestTwitt(out:ActorRef,name:String,count:Int,resultHandler: ResultHandler)
case class MyMessageResponse(message:String)

object MyMessage {
 val UNKNOWN_MSG:String = "unknownMsg"
 val TWITT_DONE_MSG:String = "twittDone"
}
