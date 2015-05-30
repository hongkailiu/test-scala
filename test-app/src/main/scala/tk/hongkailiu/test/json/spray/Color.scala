package tk.hongkailiu.test.json.spray

import spray.json.DefaultJsonProtocol

/**
 * Created by hongkailiu on 2015-05-20.
 */
case class Color(name: String, red: Int, green: Int, blue: Int)

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val colorFormat = jsonFormat4(Color)
}
