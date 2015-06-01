package helper

import Messages.{MyMessage, MyMessageRequestTwitt, MyMessageResponse}
import actor.AkkaTestkitSpecs2Support
import akka.actor.{ActorRef, ActorSystem, Props}
import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable._
import org.specs2.time.NoTimeConversions

import scala.concurrent.duration._

/**
 * Created by hongkailiu on 2015-05-30.
 */
class MyActorTest extends Specification with NoTimeConversions with IsolatedMockFactory {
  sequential
  // forces all tests to be run sequentially
  val system = ActorSystem("HelloAkkaSpec")
  val mockTwitterHelper: TwitterHelper = mock[TwitterHelperImpl]
  //does not work yet with spec2 version
  //mockTwitterHelper.request(Matchers.any[ActorRef],Matchers.anyString(),Matchers.anyInt(),Matchers.any[ResultHandler]) returns Unit
  //mockTwitterHelper.request(Matchers.any[ActorRef],Matchers.anyString(),Matchers.anyInt(),Matchers.any[ResultHandler]) returns(())
  //the following one is working, but less cool. NOT SCALA-LIKE and still in java
  //org.mockito.Mockito.doNothing().when(mockTwitterHelper).request(Matchers.any[ActorRef],Matchers.anyString(),Matchers.anyInt(),Matchers.any[ResultHandler])
  //so we use scalamock instead

  val unitUnderTest = system.actorOf(Props(new MyActor(mockTwitterHelper)), "myActor")

  "MyActor" should {

    "respond with twitt-request messages" in new AkkaTestkitSpecs2Support(system) {
      (mockTwitterHelper.request _).expects(*, *, *, *).returning(Unit).once
      within(1 second) {
        val msg = MyMessageRequestTwitt(null.asInstanceOf[ActorRef], null.asInstanceOf[String], 0, null.asInstanceOf[ResultHandler])
        unitUnderTest ! msg
        expectMsgType[MyMessageResponse].message must be equalTo MyMessage.TWITT_DONE_MSG
        //there was one(mockTwitterHelper).request(Matchers.any[ActorRef],Matchers.anyString(),Matchers.anyInt(),Matchers.any[ResultHandler])

      }
    }

    "respond with unknown messages" in new AkkaTestkitSpecs2Support(system) {
      within(1 second) {
        val msg = "msg"
        unitUnderTest ! msg
        expectMsgType[MyMessageResponse].message must be equalTo MyMessage.UNKNOWN_MSG

      }
    }

    "not respond with MyMessageResponse" in new AkkaTestkitSpecs2Support(system) {
      within(1 second) {
        unitUnderTest ! MyMessageResponse("testMsg")
        expectNoMsg()
      }
    }

  }


}
