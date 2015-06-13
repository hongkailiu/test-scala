package controllers

import javax.inject.Inject

import daos.PersonDAO
import services.PersonService
import models.Person
import play.api.mvc.{BodyParsers, Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._

class Persons @Inject() (personService: PersonService) extends Controller {
  //http://localhost:9000/persons/all
  def list = Action {
    //Ok(views.html.index("Hello Play Framework: list"))
    val json = Json.toJson(personService.list)
    Ok(json)
  }

  def show(id:Long) = Action {
    Ok(views.html.index("Hello Play Framework: show: " + id))
  }

  def save = Action(BodyParsers.parse.json) { request =>
    val personResult = request.body.validate[Person]
    personResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      person => {
        // TODO Person.save(place)
        personService.save(person)
        Ok(Json.obj("status" ->"OK", "message" -> ("Person '"+person.name+"' saved.") ))
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
