package tk.hongkailiu.test.tutorial.twitter.link5

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * Created by hongkailiu on 2015-05-30.
 */

class Link5 {

}

object Link5 {

  val logger = Logger(LoggerFactory.getLogger(classOf[Link5]))


  def myFun(a: Any): Unit = {
    if (a.isInstanceOf[String]) {
      logger.info(a.asInstanceOf[String])
    }
    if (a.isInstanceOf[Int]) {
      logger.info(a.asInstanceOf[Int] + "")
    }
  }

  def drop1[A](l: List[A]) = l.tail

  def count(l: List[_]) = l.size

  val getTweet: (Bird => String) = ((a: Animal) => a.sound )
  val hatch: (() => Bird) = (() => new Chicken )

}


class Animal {
  val sound = "rustle"
}

class Bird extends Animal {
  override val sound = "call"
}

class Chicken extends Bird {
  override val sound = "cluck"
}

class MyChicken extends Chicken {
  override val sound = "myCluck"
}
