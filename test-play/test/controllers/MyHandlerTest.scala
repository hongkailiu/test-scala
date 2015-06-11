package controllers

import akka.actor.ActorSystem
import akka.testkit.TestProbe
import org.joda.time.DateTime
import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable.Specification
import scala.concurrent.duration._

import twitter4j._


/**
 * Created by hongkailiu on 2015-06-06.
 */
class MyHandlerTest extends Specification with IsolatedMockFactory  {
  sequential

  implicit val system = ActorSystem("MyHandlerTest")
  val testProbe: TestProbe = TestProbe()
  val mockQueryResult: QueryResult = mock[QueryResult]
  val statusList: java.util.List[Status] = new java.util.ArrayList[Status]()
  val mockStatus0: Status = mock[Status]
  val mockStatus1: Status = mock[Status]
  val mockStatus2: Status = mock[Status]
  val mockStatus3: Status = mock[Status]
  var unitUnderTest: MyHandler = new MyHandler(testProbe.ref)

  val mockQueryResult1: QueryResult = mock[QueryResult]
  val statusList1: java.util.List[Status] = new java.util.ArrayList[Status]()


  "MyHandler" should {
    "handle query result well" in {

      setupMockBehavior()
      unitUnderTest.handle(mockQueryResult)
      var results:Seq[String]=Seq()
      testProbe.receiveWhile(new FiniteDuration(1, SECONDS)) {
        case s:String => {
          results = results :+ s
        }
      }
      results.size should beEqualTo(3)

      unitUnderTest.handle(mockQueryResult1)
      val dummy=testProbe.expectNoMsg(new FiniteDuration(1, SECONDS))
      dummy should be equalTo(())
    }
  }

  def setupMockBehavior() {
    (mockQueryResult.getTweets _).expects().returning(statusList).once
    (mockQueryResult1.getTweets _).expects().returning(statusList1)//.once

    (mockStatus0.getCreatedAt _).expects().returning(new DateTime(1980, 4, 10, 3, 30).toDate)
    (mockStatus0.getId _).expects().returning(new java.lang.Long(1000).longValue())
    (mockStatus0.getText _).expects().returning("haha")

    (mockStatus1.getCreatedAt _).expects().returning(new DateTime(1980, 4, 10, 3, 30).toDate)
    (mockStatus1.getId _).expects().returning(new java.lang.Long(1001).longValue())
    (mockStatus1.getText _).expects().returning("haha")

    (mockStatus2.getCreatedAt _).expects().returning(new DateTime(1980, 4, 10, 3, 30).toDate)
    (mockStatus2.getId _).expects().returning(new java.lang.Long(1002).longValue())
    (mockStatus2.getText _).expects().returning("haha")

    (mockStatus3.getCreatedAt _).expects().returning(new DateTime(1980, 4, 10, 3, 30).toDate)
    (mockStatus3.getId _).expects().returning(new java.lang.Long(1002).longValue())
    (mockStatus3.getText _).expects().returning("haha")

    statusList.add(mockStatus0)
    statusList.add(mockStatus1)
    statusList.add(mockStatus2)

    statusList1.add(mockStatus3)
  }
}
