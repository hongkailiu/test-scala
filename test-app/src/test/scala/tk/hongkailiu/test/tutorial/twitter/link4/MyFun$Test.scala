package tk.hongkailiu.test.tutorial.twitter.link4

import org.scalatest.FunSuite

/**
 * Created by ehongka on 5/14/15.
 */
class MyFun$Test extends FunSuite {
  test("compose") {
    assert(MyFun.fComposeG("yay") === "f(g(yay))")
    assert(MyFun.fAndThenG("yay") === "g(f(yay))")

    assert(true === MyFun.one.isDefinedAt(1))
    assert(false === MyFun.one.isDefinedAt(2))
    assert("one" === MyFun.one(1))
    assert("something else" === MyFun.partial(5))
  }
}
