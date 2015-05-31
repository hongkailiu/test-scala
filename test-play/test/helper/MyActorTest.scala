package helper

import Messages.{MyMessage, MyMessageResponse}
import actor.AkkaTestkitSpecs2Support
import akka.actor.{Props, ActorSystem}
import akka.pattern.ask
import akka.testkit.{ImplicitSender, TestProbe, TestActorRef}
import org.specs2.mutable._
import org.specs2.time.NoTimeConversions
import scala.concurrent.duration._
/**
 * Created by hongkailiu on 2015-05-30.
 */
class MyActorTest extends Specification with NoTimeConversions {
  sequential // forces all tests to be run sequentially
  val system = ActorSystem("HelloAkkaSpec")
  val unitUnderTest = system.actorOf(Props[MyActor], "myActor")

  "MyActor" should {
    "respond with unknown messages" in new AkkaTestkitSpecs2Support(system) {
      within(1 second) {
        val msg = "msg"
        unitUnderTest ! msg
        expectMsgType[MyMessageResponse].message must be equalTo MyMessage.UNKNOWN_MSG

      }
    }
  }


}
