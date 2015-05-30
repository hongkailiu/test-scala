package tk.hongkailiu.test.datastructure.tree

/**
 * Created by hongkailiu on 2015-05-30.
 */
trait Tree[A] {
  val value:A
  val children : List[Tree[A]]
}

trait Leaf[A] extends Tree[A] {
  val children = Nil
}

trait BinaryTree[A] extends Tree[A] {
  def getLeft() : BinaryTree[A]
  def getRight() : BinaryTree[A]
  def setLeft():Unit
  def setRight() :Unit
}
