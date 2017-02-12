package arithmeticExpressions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NodeSuite extends FunSuite {

  test("eval on a leaf node returns the value in the leaf") {
    val leaf = new LeafNode(1)
    val result = leaf.eval
    assert(result == 1)
  }

  test("eval on an addition node returns the sum of the leaves") {
    val node = new OpNode(Addition, new LeafNode(1), new LeafNode(2))
    val result = node.eval
    assert(result == 3)
  }

  test("eval on a subtraction node returns the difference of the leaves") {
    val node = new OpNode(Subtraction, new LeafNode(1), new LeafNode(2))
    val result = node.eval
    assert(result == -1)
  }

  test("eval on a multiplication node returns the product of the leaves") {
    val node = new OpNode(Multiplication, new LeafNode(2), new LeafNode(2))
    val result = node.eval
    assert(result == 4)
  }

  test("eval on a division node returns the quotient of the leaves") {
    val node = new OpNode(Division, new LeafNode(6), new LeafNode(2))
    val result = node.eval
    assert(result == 3)
  }

  test("implicit conversion from Int to LeafNode") {
    val node = new OpNode(Addition, 1, 2)
    val result = node.eval
    assert(result == 3)
  }

  test("eval on a tree with 2 levels gives the correct result") {
    // (1+2) * (5 - 3)
    val node = new OpNode(Multiplication, new OpNode(Addition, 1, 2), new OpNode(Subtraction, 5, 3))
    val result = node.eval
    assert(result == 6)
  }

  test("implicit conversion between string and operation") {
    val node = new OpNode("+", 1, 2)
    val result = node.eval
    assert(result == 3)
  }
}
