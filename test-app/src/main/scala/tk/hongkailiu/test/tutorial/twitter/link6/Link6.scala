package tk.hongkailiu.test.tutorial.twitter.link6

import scala.language.implicitConversions

/**
 * Created by hongkailiu on 2015-05-30.
 */
class Link6 {
  // needed in type conversion in max defined below
  implicit def strToInt(x: String) = x.toInt

  def max(s:String, i:Int) :Int = {
    math.max(s, i)
  }
}


object Link6 {

}
