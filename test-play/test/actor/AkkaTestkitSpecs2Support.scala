package actor

import akka.actor.ActorSystem
import akka.testkit._
import org.specs2.specification._
import scala.concurrent.duration._
/**
 * Created by hongkailiu on 2015-05-30.
 */
abstract class AkkaTestkitSpecs2Support(_system: ActorSystem) extends TestKit(_system)
with ImplicitSender with After {
  // make sure we shut down the actor system after all tests have run
  def after = {
    system.shutdown()
    system.awaitTermination(10.seconds)
  }
}
