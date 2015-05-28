package DAO

import models.Person
import org.slf4j.LoggerFactory
import play.api.Logger

/**
 * Created by ehongka on 5/22/15.
 */
class PersonDAOImpl extends PersonDAO {

  override def save(person: Person): Unit = {
    Logger.info("save")
  }

  override def list(): List[Person] = {
    List(
      Person(3, "hk"),
      Person(6, "elle")
    )
  }
}


trait PersonDAO {
  def save(person:Person)
  def list():List[Person]
}
