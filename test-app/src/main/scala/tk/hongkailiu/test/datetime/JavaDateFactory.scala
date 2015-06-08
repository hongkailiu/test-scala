package tk.hongkailiu.test.datetime

import org.joda.time.DateTime

/**
 * Created by hongkailiu on 2015-06-06.
 */
trait JavaDateFactory {

  def generateDate(year: Int, month: Int, day: Int, hour: Int, minute: Int): java.util.Date = {
    new DateTime(year, month, day, hour, minute).toDate
  }
}

object JavaDateFactory extends JavaDateFactory {

}
