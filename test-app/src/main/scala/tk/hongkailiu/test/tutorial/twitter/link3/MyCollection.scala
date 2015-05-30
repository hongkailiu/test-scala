package tk.hongkailiu.test.tutorial.twitter.link3

/**
 * Created by ehongka on 5/13/15.
 */
object MyList {
  val numbers = List(1, 2, 3, 4)

  def mul2(numbers: List[Int]): List[Int] = {
    // anonymous func
    numbers.map((i: Int) => i * 2)
  }

  def timesTwo(i: Int): Int = i * 2

  def mul2Cooler(numbers: List[Int]): List[Int] = {
    // named func
    numbers.map(timesTwo)
  }

  def mul2FE(numbers: List[Int]): Unit = {
    // immutable list, not do sth. like this
    numbers.foreach((i: Int) => i * 2)
  }

  def filterOutEven(numbers: List[Int]): List[Int] = {
    numbers.filter((i: Int) => i % 2 == 0)
  }

  def isEven(i: Int): Boolean = i % 2 == 0

  def filterOutEvenCooler(numbers: List[Int]): List[Int] = {
    numbers.filter(isEven _)
  }

  def zip(numbers: List[Int]): List[(Int, String)] = {
    numbers.zip(List("a", "b", "c"))
  }

  def foldLeft(numbers:List[Int]) : Int = {
    // m start with 0; n is from numbers(0) to numbers(numbers.size-1)
    numbers.foldLeft(0) { (m: Int, n: Int) => {println("m: " + m + " n: " + n); m + n }}
  }

  def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
    numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
      fn(x) :: xs
    }
  }
}


object MySet {
  val numbers = Set(1, 1, 2)
}

object MyTuple {
  val hostPort = ("localhost", 80)
  val hostPort1 = 1 -> 2
}

object MyMap {
  val map1 = Map(1 -> 2)
  val map2 = Map("foo" -> "bar")
  val map3 = map2 + ("a" -> "b")
}

object MyOption {
  private val map1 = Map(1 -> 2, 3 -> 4)
  val op: Option[Int] = map1.get(1)

  def mul2(op: Option[Int]): Int = {
    val result: Int = if (op.isDefined) {
      op.get * 2
    } else {
      0
    }
    result
  }

  def mul2Better(op: Option[Int]): Int = {
    val result: Int = op match {
      case Some(n) => n * 2
      case None => 0
    }
    result
  }
}
