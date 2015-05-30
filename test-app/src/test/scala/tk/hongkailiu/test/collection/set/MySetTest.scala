package tk.hongkailiu.test.collection.set

import org.scalatest.FunSuite

/**
 * Created by ehongka on 5/21/15.
 */
class MySetTest extends FunSuite {

  test("trivial") {
    assert(1===1)
  }

  test("test set") {
    val fruit = Set("apple", "orange", "peach", "banana")
    assert(fruit !== null)
    assert(fruit.isEmpty === false)
    assert(fruit.size === 4)
    val fruit1 = fruit
    assert(fruit1.hashCode() === fruit.hashCode())

    val fruit2 = fruit + "kiwi"
    println("fruit2: " + fruit2)
    println("fruit1: " + fruit1)
    assert(fruit1.hashCode() === fruit.hashCode())

    fruit.foreach { println }
  }
}
