package controllers

import Messages.MyMessageRequestTwitt
import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, TestProbe}
import helper.{MyWebSocketActorSupporter, MyWebSocketActorSupporterImpl}
import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable.Specification

import scala.concurrent.duration._

/**
  * Created by hongkailiu on 2015-06-08.
  */
class MyWebSocketActorTest extends Specification with IsolatedMockFactory {
   sequential

   implicit val system = ActorSystem("MyHandlerTest")
   val testProbe: TestProbe = TestProbe()
   val dummyTestProbe: TestProbe = TestProbe()
   val name: String = "name"
   val mockMyWebSocketActorSupporter: MyWebSocketActorSupporter = mock[MyWebSocketActorSupporterImpl]

   // nice way to get an instance of MyHandler
   // not being used for macking its behaviors here although
   class MyTestHandler extends MyHandler(dummyTestProbe.ref)
   val mockMyHandler: MyHandler = mock[MyTestHandler]


   def setupMockBehavior() = {
     (mockMyWebSocketActorSupporter.getAkkaSystem _).expects().returning(system).once
     (mockMyWebSocketActorSupporter.getActorRef _).expects(*).returning(testProbe.ref).once
     (mockMyWebSocketActorSupporter.getMyHandler _).expects(dummyTestProbe.ref).returning(mockMyHandler).once
     (mockMyWebSocketActorSupporter.getInterval _).expects().returning(1).once
   }

   "MyWebSocketActor" should {
     "work" in {
       //true should beTrue
       setupMockBehavior()
       TestActorRef(new MyWebSocketActor(dummyTestProbe.ref,name,mockMyWebSocketActorSupporter))
       var results:Seq[MyMessageRequestTwitt]=Seq()
       testProbe.receiveWhile(new FiniteDuration(2300, MILLISECONDS)) {
         case MyMessageRequestTwitt(out, name, 3, myHandler) => {
           results = results :+ MyMessageRequestTwitt(out, name, 3, myHandler)
         }
       }
       results.size should beEqualTo(3)
     }
   }
 }
