import tk.hongkailiu.test.guice.{Person, PersonDAO, PersonDAOImpl}
import org.joda.time.DateTime

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    //testPerson()
  }

  def testPerson():Unit={
    val personDAO:PersonDAO=new PersonDAOImpl
    personDAO.save(new Person(3,"hk", new DateTime(1980,4,10,3,23)))
  }
}
