package tk.hongkailiu.test.tutorial.twitter.link5

import org.scalatest.FunSuite

/**
 * Created by hongkailiu on 2015-05-30.
 */
class Link5$Test extends FunSuite {
  test("trivail") {
    assert(1 === 1)
  }
  test("list") {
    val list = Link5.list;
    assert(list.isEmpty === false)
  }

}
