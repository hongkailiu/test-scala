import helper.TwitterHelper
import play.api._
object Global extends GlobalSettings {
  override def onStart(app: Application) {
    Logger.info("Application has started")
    //TwitterHelper.start
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }


}
