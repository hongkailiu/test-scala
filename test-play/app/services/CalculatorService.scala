package services

import javax.inject.Inject

import com.google.inject.ImplementedBy
import daos.{PersonDAOImpl, PersonDAO}
import models.Person
import tk.hongkailiu.test.tutorial.twitter.link1.Calculator

/**
 * Created by ehongka on 5/22/15.
 */
class CalculatorServiceImpl @Inject() (calculator:Calculator) extends CalculatorService {
  override def add23(arg:Int):Int = {
    calculator.add(23, arg)
  }
}

@ImplementedBy(classOf[CalculatorServiceImpl])
trait CalculatorService {
  def add23(arg:Int):Int
  //def save(person: Person)
}


