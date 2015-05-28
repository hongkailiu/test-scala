package controllers

import play.api.Logger
import play.api.libs.iteratee.{Concurrent, Enumerator, Iteratee}
import play.api.mvc.{WebSocket, Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global

trait Application extends Controller {
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

object Application extends Controller with Application
