package tk.hongkailiu.test.tutorial.twitter.link4

/**
 * Created by ehongka on 5/14/15.
 */
object MyFun {
  def f(s: String) = "f(" + s + ")"
  def g(s: String) = "g(" + s + ")"
  val fComposeG = f _ compose g _

  val fAndThenG = f _ andThen g _

  val one: PartialFunction[Int, String] = { case 1 => "one" }
  val two: PartialFunction[Int, String] = { case 2 => "two" }
  val three: PartialFunction[Int, String] = { case 3 => "three" }
  val wildcard: PartialFunction[Int, String] = { case _ => "something else" }

  // cool part
  val partial = one orElse two orElse three orElse wildcard
}

