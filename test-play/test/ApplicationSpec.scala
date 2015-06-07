import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Hello Play Framework")
    }

    "send all persons" in new WithApplication{
      val home = route(FakeRequest(GET, "/persons/all")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "application/json")
      contentAsString(home) must contain ("hk")
    }

    "send some person" in new WithApplication{
      val id:Long=23
      val home = route(FakeRequest(GET, "/persons/" + id)).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain (id.toString)
    }

    "respond to twitter page" in new WithApplication{
      val hashTag:String="23"
      val home = route(FakeRequest(GET, "/twitter/show/" + hashTag)).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain (hashTag)
    }

  }
}
