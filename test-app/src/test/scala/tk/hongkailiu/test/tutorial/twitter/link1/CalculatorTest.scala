package tk.hongkailiu.test.tutorial.twitter.link1

import org.scalatest.FunSuite

/**
 * Created by ehongka on 5/14/15.
 */
class CalculatorTest extends FunSuite {

  test("cal") {
    val calc = new Calculator
    // this won't work: null has to be after !==
    //assert(null!==calc)

    assert(calc!==null)
    assert(3 === calc.add(1, 2))

    assert(calc.brand === "HP")

  }

}
