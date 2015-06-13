package daos

import com.google.inject.ImplementedBy
import models.Person
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

@ImplementedBy(classOf[PersonDAOImpl])
trait PersonDAO {
  def save(person:Person)
  def list():List[Person]
}
