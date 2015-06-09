package controllers

import Messages.{MyMessage, MyMessageResponse, MyMessageRequestTwitt}
import akka.actor.ActorRef
import helper.ResultHandler
import org.specs2.mutable.Specification

/**
 * Created by hongkailiu on 2015-06-08.
 */
class MyWebSocketActorTest extends Specification {
  "MyWebSocketActor" should {
    "work" in {
      true should beTrue
    }
  }
}
