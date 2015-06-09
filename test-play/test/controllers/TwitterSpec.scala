package controllers

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class TwitterSpec extends Specification {

  "Twitter controller" should {

    "respond to twitter page" in new WithApplication{
      val hashTag:String="23"
      val home = route(FakeRequest(GET, "/twitter/show/" + hashTag)).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain (hashTag)
    }

  }
}
