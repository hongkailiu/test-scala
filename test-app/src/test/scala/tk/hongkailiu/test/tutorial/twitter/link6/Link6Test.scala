package tk.hongkailiu.test.tutorial.twitter.link6

import org.scalatest.FunSuite

/**
 * Created by hongkailiu on 2015-05-30.
 */
class Link6Test extends FunSuite {

  val  unitUnderTest = new Link6

  test("test") {
    assert(unitUnderTest.max("33", 32)===33)
  }

}
