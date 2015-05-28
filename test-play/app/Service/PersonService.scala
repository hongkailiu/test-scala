package Service

import DAO.{PersonDAOImpl, PersonDAO}
import models.Person

/**
 * Created by ehongka on 5/22/15.
 */
class PersonServiceImpl(personDAO:PersonDAO) extends PersonService {
  override def list(): List[Person] = {
    personDAO.list()
  }
}


trait PersonService {
  def list():List[Person]
}


object PersonService {
  val personDAO:PersonDAO = new PersonDAOImpl
  val personService: PersonService = new PersonServiceImpl(personDAO)
  def list(): List[Person] = {
    personService.list()
  }
}
