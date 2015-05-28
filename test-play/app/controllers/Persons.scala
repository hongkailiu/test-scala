package controllers

import Service.PersonService
import models.Person
import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Persons extends Controller {
  //http://localhost:9000/persons/all
  def list = Action {
    //Ok(views.html.index("Hello Play Framework: list"))
    val json = Json.toJson(PersonService.list)
    Ok(json)
  }

  def show(id:Long) = Action {
    Ok(views.html.index("Hello Play Framework: show: " + id))
  }

  implicit val placeWrites: Writes[Person] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String]
    )(unlift(Person.unapply))
}
