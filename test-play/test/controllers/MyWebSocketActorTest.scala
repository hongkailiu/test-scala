package controllers

import Messages.{MyMessage, MyMessageResponse, MyMessageRequestTwitt}
import akka.actor.{Props, ActorSystem, ActorRef}
import akka.testkit.{TestActorRef, TestActor, TestProbe}
import helper.{SystemProvider, ResultHandler}
import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable.Specification
import org.specs2.time.NoTimeConversions

/**
 * Created by hongkailiu on 2015-06-08.
 */
class MyWebSocketActorTest extends Specification with NoTimeConversions with IsolatedMockFactory {
  sequential

  implicit val system = ActorSystem("MyHandlerTest")
  val testProbe: TestProbe = TestProbe()
  val name: String = "name"
  val systemProvider: SystemProvider = mock[SystemProvider]
  //val actorRef = TestActorRef(new MyWebSocketActor(testProbe.ref,name,systemProvider))

  "MyWebSocketActor" should {
    "work" in {
      true should beTrue
    }
  }
}
