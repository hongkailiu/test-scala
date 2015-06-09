package helper

import Messages.{MyMessage, MyMessageRequestTwitt, MyMessageResponse}
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable._
import org.specs2.time.NoTimeConversions

import scala.concurrent.duration._

/**
 * Created by hongkailiu on 2015-05-30.
 */
class MyActorTest extends TestKit(ActorSystem("HelloAkkaSpec")) with SpecificationLike with NoTimeConversions with IsolatedMockFactory with ImplicitSender {
  sequential

  val mockTwitterHelper: TwitterHelper = mock[TwitterHelperImpl]
  val unitUnderTest = TestActorRef(Props(new MyActor(mockTwitterHelper)), "myActor")

  "MyActor" should {

    "respond with twitt-request messages" in {
      (mockTwitterHelper.request _).expects(*, *, *, *).returning(Unit).once
      val msg = MyMessageRequestTwitt(null.asInstanceOf[ActorRef], null.asInstanceOf[String], 0, null.asInstanceOf[ResultHandler])
      unitUnderTest ! msg
      expectMsgType[MyMessageResponse](1 second).message must be equalTo MyMessage.TWITT_DONE_MSG
    }

    "respond with unknown messages" in {
      val msg = "msg"
      unitUnderTest ! msg
      expectMsgType[MyMessageResponse](1 second).message must be equalTo MyMessage.UNKNOWN_MSG
    }

    "not respond with MyMessageResponse" in {
      unitUnderTest ! MyMessageResponse("testMsg")
      val dummy = expectNoMsg(1 second)
      dummy should be equalTo (())
    }
  }


}
