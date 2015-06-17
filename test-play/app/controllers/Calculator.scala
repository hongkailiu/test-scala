package controllers

import javax.inject.Inject

import models.Person
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{Action, BodyParsers, Controller}
import services.{CalculatorService, PersonService}

class Calculator @Inject() (calculatorService: CalculatorService) extends Controller {
  //http://localhost:9000/cal/add23/<number>
  def add23(arg:Int)  = Action {
    val json = Json.toJson(calculatorService.add23(arg))
    Ok(json)
  }
}
