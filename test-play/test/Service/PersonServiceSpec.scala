package Service

import DAO.PersonDAOImpl
import models.Person
import org.mockito.Mockito

import org.scalatest.mock.MockitoSugar
import org.specs2.mutable.Specification

/**
 * Created by ehongka on 5/22/15.
 */
class PersonServiceSpec extends Specification with MockitoSugar {
  "PersonService#list" should {
    "be working" in {
      val personDAO = mock[PersonDAOImpl]

      Mockito.when(personDAO.list()) thenReturn List(
        Person(3, "test-hk"),
        Person(6, "test-elle")
      )
      val personService = new PersonServiceImpl(personDAO)
      val list = personService.list()
      list.size must be equalTo(2)
      list.contains(Person(3, "test-hk")) must beTrue
      list.contains(Person(3, "test-hk")) must beTrue
    }
  }
}
