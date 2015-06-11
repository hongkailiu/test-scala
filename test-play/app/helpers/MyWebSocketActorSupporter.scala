package helpers

import akka.actor.{ActorContext, Props, ActorRef, ActorSystem}
import controllers.MyHandler

/**
 * Created by hongkailiu on 2015-06-08.
 */
trait MyWebSocketActorSupporter {
  def getAkkaSystem: ActorSystem
  def getActorRef(context : ActorContext): ActorRef
  def getMyHandler(out: ActorRef): MyHandler
  def getInterval: Int
}

class MyWebSocketActorSupporterImpl extends MyWebSocketActorSupporter {
  override def getAkkaSystem: ActorSystem = {
    play.libs.Akka.system
  }
  override def getActorRef(context : ActorContext): ActorRef = {
    context.actorOf(Props(new MyActor(new TwitterHelperImpl())), "myActor")
  }
  override def getMyHandler(out: ActorRef): MyHandler = {
    new MyHandler(out)
  }
  override def getInterval = 10
}
