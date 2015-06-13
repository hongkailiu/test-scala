package controllers

import play.api._
import play.api.libs.iteratee.{Iteratee, Enumerator, Concurrent}
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {
  this: Controller =>
  def index = Action {
    Ok(views.html.index("Hello Play Framework"))
  }

  def wsHello = WebSocket.using[String] { request => {
    Logger.info(s"wsEcho, client connected.")
    var channel: Option[Concurrent.Channel[String]] = None
    val outEnumerator: Enumerator[String] = Concurrent.unicast(c => channel = Some(c))

    val inIteratee: Iteratee[String, Unit] = Iteratee.foreach[String](receivedString => {
      // send string back
      Logger.info(s"wsEcho, received: $receivedString")
      channel.foreach(_.push(receivedString))
    })

    (inIteratee, outEnumerator)
  }
  }
}
