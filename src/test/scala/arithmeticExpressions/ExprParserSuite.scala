package arithmeticExpressions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ExprParserSuite extends FunSuite {

  test("should parse a single number") {
    val result = ExprParser.parse("42")
    assert(result == LeafNode(42))
  }

  test("should parse a single operation") {
    val result = ExprParser.parse("1 + 2")
    assert(result == OpNode(Addition, 1, 2))
  }

  test("should parse a single operation with many numbers") {
    val result = ExprParser.parse("1 * 2 * 3")
    assert(result == OpNode("*", OpNode("*", 1, 2), 3))
  }

  test("should parse operations in the correct order") {
    val result = ExprParser.parse("1 + 2 - 3 - 4")
    assert(result.eval == -4)
  }

  test("should respect operation associativity") {
    val result = ExprParser.parse("1 + 2*3 + 4")
    assert(result.eval == 11)
  }

  test("should parse subtractions and additions") {
    val result = ExprParser.parse("1 + 2 - 3 - 4")
    assert(result.eval == -4)
  }

  test("should parse divisions") {
    val result = ExprParser.parse("1*3 / 3 + 1")
    assert(result.eval == 2)
  }

  test("should parse longer expressions") {
    val result = ExprParser.parse("1000*0 + 1 + 2 + 3*1 + 2*2 + 25 / 5 - 15")
    assert(result.eval == 0)
  }

  // 1 + 2 + 3 + 4
  // --> ((1 + 2) + 3) + 4

  // 1 + 2 * 3 + 4
  // --> (1 + (2 * 3)) + 4

}
