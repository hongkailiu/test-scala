package Messages

import akka.actor.ActorRef
import helper.ResultHandler
import models.Twitt

/**
 * Created by hongkailiu on 2015-05-25.
 */
case class MyMessageTwittRequest(out:ActorRef,name:String,count:Int,resultHandler: ResultHandler)
case class MyMessageResponse(message:String)

object MyMessage {
 val UNKNOWN_MSG:String = "unknownMsg"
}
