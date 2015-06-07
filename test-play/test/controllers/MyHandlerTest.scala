package controllers

import java.util
import java.util.Date

import actor.AkkaTestkitSpecs2Support
import akka.actor.{Actor, Props, ActorSystem, ActorRef}
import akka.testkit.{TestProbe, TestActorRef}
import helper.MyActor
import org.joda.time.DateTime
import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeEach
import org.specs2.time.NoTimeConversions
import twitter4j._
import scala.concurrent.duration._

/**
 * Created by hongkailiu on 2015-06-06.
 */
class MyHandlerTest extends Specification with NoTimeConversions with IsolatedMockFactory {
  sequential


  val system = ActorSystem("MyHandlerTest")
  //val testActorRef = system.actorOf(Props(new TestActor()), "testActor")
  var unitUnderTest: MyHandler = null

  val mockQueryResult: QueryResult =mock[QueryResult]
  val statusList: java.util.List[Status] = new java.util.ArrayList[Status]()
  val mockStatus0:Status= mock[Status]
  val mockStatus1:Status= mock[Status]
  val mockStatus2:Status= mock[Status]

  "MyHandler" should {
    "handle query result well" in new AkkaTestkitSpecs2Support(system) {
      //val testActorRef = system.actorOf(Props(new TestActor()), "testActor")
      //val testActorRef = TestActorRef(new TestActor, "out")
      val testProbe:TestProbe = TestProbe()
      unitUnderTest = new MyHandler(testProbe.ref)

      setupMockBehavior()
      within(3 second) {
        unitUnderTest.handle(mockQueryResult)
        testProbe.receiveN(3)
      }


      //val list: java.util.List[Status] = mockQueryResult.getTweets
      //list.contains(mockStatus0) must beTrue
    }
  }

  def setupMockBehavior() {



    (mockQueryResult.getTweets _).expects().returning(statusList).once

    (mockStatus0.getCreatedAt _).expects().returning(new DateTime(1980,4,10,3,30).toDate)
    (mockStatus0.getId _).expects().returning(new java.lang.Long(1000).longValue())
    (mockStatus0.getText _).expects().returning("haha")

    (mockStatus1.getCreatedAt _).expects().returning(new DateTime(1980,4,10,3,30).toDate)
    (mockStatus1.getId _).expects().returning(new java.lang.Long(1001).longValue())
    (mockStatus1.getText _).expects().returning("haha")

    (mockStatus2.getCreatedAt _).expects().returning(new DateTime(1980,4,10,3,30).toDate)
    (mockStatus2.getId _).expects().returning(new java.lang.Long(1002).longValue())
    (mockStatus2.getText _).expects().returning("haha")

    statusList.add(mockStatus0)
    statusList.add(mockStatus1)
    statusList.add(mockStatus2)
  }
}

class TestActor extends Actor {
  def receive = {
    case x:Any => {println(x)
    }
  }
}
