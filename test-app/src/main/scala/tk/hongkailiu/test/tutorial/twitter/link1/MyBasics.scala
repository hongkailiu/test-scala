package tk.hongkailiu.test.tutorial.twitter.link1

/**
 * Created by ehongka on 5/14/15.
 */
object MyBasics {

  val two = 1 + 1
  var name = "steve"

  def addOne(m: Int): Int = m + 1
  val three = addOne(2)

  val addOneCooler = (x: Int) => x + 1

  def timesTwo(i: Int): Int = {
    println("hello world")
    i * 2
  }


  def adder(m: Int, n: Int) = m + n
  val add2 = adder(2, _:Int)

  def multiply(m: Int)(n: Int): Int = m * n

  val timesTwoCurried = multiply(2) _

  def capitalizeAll(args: String*) = {
    args.map { arg =>
      arg.capitalize
    }
  }
}
