package tk.hongkailiu.test.akka

import akka.actor._
import scala.concurrent.duration._

case object Greet
case class WhoToGreet(who: String)
case class Greeting(message: String)

/**
 * https://github.com/typesafehub/activator-hello-akka/blob/master/src/main/scala/HelloAkkaScala.scala
 */
class Greeter extends Actor {
  var greeting = ""

  def receive = {
    case WhoToGreet(who) => greeting = s"hello, $who"
    case Greet           => sender ! Greeting(greeting) // Send the current greeting back to the sender
  }
}



