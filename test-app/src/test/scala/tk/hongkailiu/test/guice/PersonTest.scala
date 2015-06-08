package tk.hongkailiu.test.guice

import org.joda.time.DateTime
import org.scalatest.FunSuite

/**
 * Created by hongkailiu on 2015-05-20.
 */
class PersonTest extends FunSuite {

  test("trivial") {
    assert(1 === 1)
  }

  test("constructor") {
    val person = new Person(3, "hk", new DateTime(1980, 4, 10, 3, 30))
    assert(person !== null)
  }
}
