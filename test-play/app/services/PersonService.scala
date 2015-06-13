package services

import javax.inject.Inject

import com.google.inject.ImplementedBy
import daos.{PersonDAOImpl, PersonDAO}
import models.Person

/**
 * Created by ehongka on 5/22/15.
 */
class PersonServiceImpl @Inject() (personDAO:PersonDAO) extends PersonService {
  override def list(): List[Person] = {
    personDAO.list()
  }

  override def save(person: Person) = {
    personDAO.save(person)
  }
}

@ImplementedBy(classOf[PersonServiceImpl])
trait PersonService {
  def list():List[Person]
  def save(person: Person)
}


/*object PersonService {
  val personDAO:PersonDAO = new PersonDAOImpl
  val personService: PersonService = new PersonServiceImpl(personDAO)
  def list(): List[Person] = {
    personService.list()
  }
}*/
