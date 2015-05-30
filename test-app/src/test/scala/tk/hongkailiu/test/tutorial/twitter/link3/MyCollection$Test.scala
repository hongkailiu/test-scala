package tk.hongkailiu.test.tutorial.twitter.link3

import org.scalatest.FunSuite

class MyCollection$Test extends FunSuite {

  test("collection basics") {
    println(MyList.numbers)
    assert(MyList.numbers !== null)
    assert(MyList.numbers(3) === 4)

    println(MySet.numbers)
    assert(MySet.numbers !== null)
    assert(MySet.numbers.size === 2)

    println(MyTuple.hostPort)
    assert(MyTuple.hostPort !== null)
    assert(MyTuple.hostPort._1 === "localhost")
    assert(MyTuple.hostPort._2 === 80)

    println(MyTuple.hostPort1)
    assert(MyTuple.hostPort1 !== null)
    assert(MyTuple.hostPort1._1 === 1)
    assert(MyTuple.hostPort1._2 === 2)

    println(MyMap.map1)
    assert(MyMap.map1 !== null)
    assert(MyMap.map1(1) === 2)

    println(MyMap.map2)
    assert(MyMap.map2 !== null)
    assert(MyMap.map2.size === 1)

    println(MyMap.map3)
    assert(MyMap.map3 !== null)
    assert(MyMap.map3.size === 2)

    println(MyOption.op)
    val op: Option[Int] = MyOption.op;
    assert(op !== null)
    val result: Int = MyOption.mul2(op)
    println("result: " + result)
    assert(result === 4)

    val result1: Int = MyOption.mul2Better(op)
    println("result1: " + result1)
    assert(result1 === 4)

  }

  test("collection ops") {

    println(MyList.numbers)
    assert(MyList.numbers !== null)
    val result: List[Int] = MyList.mul2(MyList.numbers)
    assert(result !== null)
    assert(result.size === 4)
    println(result)
    assert(result(2) === 6)

    val result1: List[Int] = MyList.mul2(MyList.numbers)
    assert(result === result1)

    println(MyList.numbers)
    MyList.mul2FE(MyList.numbers)
    println(MyList.numbers)

    val result2: List[Int] = MyList.filterOutEven(MyList.numbers)
    println(result2)
    assert(result2.size === 2)
    assert(result2(0) === 2)

    val result3: List[Int] = MyList.filterOutEvenCooler(MyList.numbers)
    println(result3)
    assert(result2 === result3)

    val result4: List[(Int, String)] = MyList.zip(MyList.numbers)
    println(result4)
    assert(MyList.numbers.size === 4)
    assert(result4(0) === (1 -> "a"))
    assert((1, "a") === (1 -> "a"))
    assert(result4.size === 3)

    val testList1: List[Int] = List(1)
    val result5: List[(Int, String)] = MyList.zip(testList1)
    println(result5)
    assert(result5.size === 1)

    val result6: Int = MyList.foldLeft(MyList.numbers)
    assert(result6 === 10)

    val nestedNumbers = List(List(1, 2), List(3, 4))
    val result7 : List[Int] = nestedNumbers.flatten;
    println("result7: " + result7)
    assert(result7.size === 4)
    assert(result7(0) === 1)
    assert(result7(2) === 3)


    // flatMap
    val result8 : List[Int] = nestedNumbers.flatMap(x => x.map(_ * 2))
    println("result8: " + result8)
    // 可以把它看做是“先映射后扁平化”的快捷操作
    val result9 : List[Int] = nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten
    println("result9: " + result9)
    assert(result8 === result9)

    val one:Int =1
    val emptyList = List[Int]()
    println("one::emptyList: " + one::emptyList)

    val result10 : List[Int] = MyList.ourMap(MyList.numbers, MyList.timesTwo(_))
    println("result10: " + result10)
  }
}
