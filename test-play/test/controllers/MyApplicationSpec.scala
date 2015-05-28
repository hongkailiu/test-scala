package controllers

import play.api.mvc.{Controller, Result}
import play.api.test._

import scala.concurrent.Future

/**
 * Created by ehongka on 5/22/15.
 */
//@RunWith(classOf[JUnitRunner])
class MyApplicationSpec extends PlaySpecification {

  class TestController() extends Controller with controllers.Application

  "Application Page#index" should {
    "should be valid" in new WithApplication {
    //"should be valid" in {
      val controller = new TestController()
      val result: Future[Result] = controller.index().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      //val bodyText: String = "Hello"
      bodyText.contains("Hello Play Framework") must beTrue
    }
  }
}
