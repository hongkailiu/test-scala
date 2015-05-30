package tk.hongkailiu.test.json.spray

import org.scalatest.FunSuite

/**
 * Created by hongkailiu on 2015-05-20.
 */
class ColorTest extends FunSuite {
  test("trivial") {
    assert(1 === 1)

    import MyJsonProtocol._
    import spray.json._

    val colorBefore = Color("CadetBlue", 95, 158, 160)
    val json = colorBefore.toJson
    println("json: " + json)
    val colorAfter = json.convertTo[Color]
    println("color: " + colorAfter)

    assert(colorBefore===colorAfter)
  }
}
