package controllers

import services.PersonService
import models.Person
import play.api.mvc.{BodyParsers, Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._

class Persons extends Controller {
  //http://localhost:9000/persons/all
  def list = Action {
    //Ok(views.html.index("Hello Play Framework: list"))
    val json = Json.toJson(PersonService.list)
    Ok(json)
  }

  def show(id:Long) = Action {
    Ok(views.html.index("Hello Play Framework: show: " + id))
  }

  def savePlace = Action(BodyParsers.parse.json) { request =>
    val placeResult = request.body.validate[Person]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      Person => {
        // TODO Person.save(place)
        Ok(Json.obj("status" ->"OK", "message" -> ("Person '"+Person.name+"' saved.") ))
      }
    )
  }

  implicit val placeWrites: Writes[Person] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String]
    )(unlift(Person.unapply))

  implicit val placeReads: Reads[Person] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "name").read[String]
    )(Person.apply _)
}
