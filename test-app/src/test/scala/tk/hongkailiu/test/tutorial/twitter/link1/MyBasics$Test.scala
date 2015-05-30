package tk.hongkailiu.test.tutorial.twitter.link1

import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer


/**
 * Created by ehongka on 5/14/15.
 */
class MyBasics$Test extends FunSuite {
  test("add") {
    assert(2 === MyBasics.two)
    assert("steve" === MyBasics.name)

    assert(3 === MyBasics.addOneCooler(2))

    assert(6 === MyBasics.timesTwo(3))

    val i: Int = MyBasics.adder(3, 2)
    assert(5 === i)

    val i2: Int = MyBasics.add2(3)
    assert(i2 === i)

    assert(6 === MyBasics.multiply(2)(3))

    assert(6 === MyBasics.timesTwoCurried(3))

    val seq: Seq[String]  = MyBasics.capitalizeAll("rarity", "applejack")
    var expected = ArrayBuffer[String]()
    expected += "Rarity"
    expected += "Applejack"
    println("seq: " + seq)
    assert(seq.size===2)
    assert(seq(0)==="Rarity")
    assert(seq(1)==="Applejack")
    assert(expected===seq)


  }
}
