package tk.hongkailiu.test.tutorial.twitter.link5

import org.scalatest.FunSuite

/**
 * Created by hongkailiu on 2015-05-30.
 */
class Link5Test extends FunSuite {
  val list : List[Any] = 2 :: 1 :: "bar" :: "foo" :: Nil
  test("trivail") {
    assert(1 === 1)
  }
  test("list") {
    println(list)
    assert(list!==null)
    assert(list.isEmpty === false)
    assert(list.size === 4)
    assert(list(0)===2)
    assert(list(2)==="bar")

    //list.foreach(Link5.myFun(_))
    list.foreach((e:Any) => {Link5.myFun(e)})

    assert(Link5.drop1(List(1,2,3))===List(2,3))

    assert(Link5.count(list) === list.size)

    assert(Link5.getTweet(new Bird) === "call")
    assert(Link5.hatch().isInstanceOf[Chicken] === true)
    assert(Link5.hatch().isInstanceOf[Bird] === true)
    assert(Link5.hatch().isInstanceOf[Animal] === true)
    assert(Link5.hatch().isInstanceOf[MyChicken] === false)


  }

}
