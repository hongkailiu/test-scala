package Service

import DAO.PersonDAOImpl
import models.Person
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}


/**
 * Created by ehongka on 5/22/15.
 */
class PersonServiceTest extends FlatSpec with Matchers with MockFactory {
  "PersonService#list" should "be working" in {

    val personDAO = mock[PersonDAOImpl]

    (personDAO.list _).expects().returning(List(
      Person(3, "test-hk"),
      Person(6, "test-elle")
    ))

    val personService = new PersonServiceImpl(personDAO)
    val list = personService.list()
    list.size shouldBe 2
    list should have size 2
    list should contain (Person(3, "test-hk"))
    list should contain (Person(6, "test-elle"))
}

}
