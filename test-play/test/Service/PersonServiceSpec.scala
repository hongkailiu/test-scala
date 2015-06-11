package Service

import DAO.PersonDAOImpl
import models.Person
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

/**
 * Created by ehongka on 5/22/15.
 */
class PersonServiceSpec extends Specification with Mockito {
  "PersonService#list" should {
    "be working" in {
      val personDAO = mock[PersonDAOImpl]

      personDAO.list() returns List(
        Person(3, "test-hk"),
        Person(6, "test-elle")
      )


      val personService = new PersonServiceImpl(personDAO)
      val list = personService.list()
      list.size must be equalTo(2)
      list.contains(Person(3, "test-hk")) must beTrue
      list.contains(Person(3, "test-hk")) must beTrue

      there was one(personDAO).list()
    }
  }
}
