package helper

import play.api.Logger
import twitter4j.conf.ConfigurationBuilder
import twitter4j.{Query, QueryResult, TwitterFactory}

/**
 * Created by ehongka on 5/25/15.
 */
class TwitterService(hashTag: String, count: Int, resultHandler: ResultHandler) {

  val cb = new ConfigurationBuilder()
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey("Eq7V4ZPpMiF3i2saGBJkqFsVJ")
    .setOAuthConsumerSecret("cS1odMViNWmeA6ixCY2ar8pC69k9IrMfwwNS9zDS4jmBItuXTS")
    .setOAuthAccessToken("37640155-Rn3ECsPwrWcvllHrKgRDBI1nQrIoxUW0cfUKP3z37")
    .setOAuthAccessTokenSecret("1hkGGM7IFrISjQFthjNywr9B3IlEAWaEfnInDvZyDHRn2")
  val tf = new TwitterFactory(cb.build())
  val twitter = tf.getInstance()
  val hashTagQuery = new Query("#" + hashTag)

  def query() {
    Logger.info("start============")
    hashTagQuery.count(count)
    val queryResult = twitter.search(hashTagQuery)
    resultHandler.handle(queryResult)
  }
}


trait ResultHandler {
  def handle(queryResult: QueryResult)
}
