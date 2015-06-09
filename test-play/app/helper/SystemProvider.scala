package helper

import akka.actor.ActorSystem

/**
 * Created by hongkailiu on 2015-06-08.
 */
trait SystemProvider {
  def getAkkaSystem: ActorSystem
}

class SystemProviderImp extends SystemProvider {
  override def getAkkaSystem: ActorSystem = {
    play.libs.Akka.system
  }
}
