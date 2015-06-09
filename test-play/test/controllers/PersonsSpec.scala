package controllers

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class PersonsSpec extends Specification {

  "Persons controller" should {

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
  }
}
