import play.api._
import play.api.mvc.RequestHeader
import play.api.mvc.Results._

import scala.concurrent.Future

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    Logger.info("Application has started")
    //TwitterHelper.start
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

  /**
   * When an exception occurs in your application, the onError operation will be called. The default is to use the internal framework error page
   * @param request the request
   * @param ex the throwable
   * @return Unit
   */
/*  override def onError(request: RequestHeader, ex: Throwable) = {
    Future.successful(InternalServerError(
      views.html.errorPage(ex.toString)
    ))
  }*/

  /**
   * If the framework doesn’t find an Action for a request, the onHandlerNotFound operation will be called
   * @param request the request
   * @return Unit
   */
/*  override def onHandlerNotFound(request: RequestHeader) = {
    Future.successful(NotFound(
      views.html.notFoundPage(request.path)
    ))
  }*/

  /**
   * The onBadRequest operation will be called if a route was found, but it was not possible to bind the request parameters
   * @param request the request
   * @param error the error
   * @return Unit
   */
/*  override def onBadRequest(request: RequestHeader, error: String) = {
    Future.successful(BadRequest("Bad Request: " + error))
  }*/
}
