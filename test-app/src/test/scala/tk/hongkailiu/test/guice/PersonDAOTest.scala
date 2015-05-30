package tk.hongkailiu.test.guice

import org.scalatest.FunSuite

/**
 * Created by hongkailiu on 2015-05-20.
 */
class PersonDAOTest extends FunSuite {

  val unitUnderTest: PersonDAO = new PersonDAOImpl

  test("save") {
    assert(unitUnderTest !== null)
  }
}
