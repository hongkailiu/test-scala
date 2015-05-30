package tk.hongkailiu.test.guice

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * Created by hongkailiu on 2015-05-20.
 */
trait PersonDAO {
  def save(person: Person): Unit
}

class PersonDAOImpl extends PersonDAO {
  val logger = Logger(LoggerFactory.getLogger(classOf[PersonDAOImpl]))

  override def save(person: Person): Unit = {
    // dummy action
    logger.info("save: " + person)
  }
}
