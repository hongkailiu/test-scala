package helpers


import org.scalamock.specs2.IsolatedMockFactory
import org.specs2.mutable.Specification
import twitter4j.QueryResult

/**
 * Created by ehongka on 6/11/15.
 */
class TwitterServiceIT extends Specification with IsolatedMockFactory {

  val resultHandler: ResultHandler = new TestResultHandler
  val unitUnderTest: TwitterService = new TwitterService("abc", 3, resultHandler)

  "TwitterService" should {
    "provide twitts" in {
      //true should beTrue
      unitUnderTest.query()
      val temp : TestResultHandler = resultHandler.asInstanceOf[TestResultHandler]
      temp.qr.getTweets must have size(be_>=(1))
      temp.qr.getTweets must have size(be_<=(3))
    }
  }
}

class TestResultHandler extends ResultHandler {
  var qr:QueryResult = null
  override def handle(queryResult: QueryResult): Unit = {
    qr = queryResult
  }
}
