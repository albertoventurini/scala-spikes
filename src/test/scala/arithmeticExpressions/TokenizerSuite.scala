package arithmeticExpressions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import arithmeticExpressions.Tokenizer._

@RunWith(classOf[JUnitRunner])
class TokenizerSuite extends FunSuite {

  test("tokenize a single number") {
    val tokens = Tokenizer.tokenize("123")
    assert(tokens.length == 1)
    assert(tokens(0) == "123")
  }

  test("tokenize numbers and operations") {
    val tokens = Tokenizer.tokenize("1+2*3")
    assert(tokens.length == 5)
    assert(tokens(0) == "1")
    assert(tokens(1) == "+")
    assert(tokens(2) == "2")
    assert(tokens(3) == "*")
    assert(tokens(4) == "3")
  }

}
