package arithmeticExpressions

object Tokenizer {

  // To do: redefine as implicit conversion on the package scope
  def tokenize(s: String): List[String] = {

    def merge[A](l1: List[A], l2: List[A]): List[A] = l1 match {
      case Nil => Nil
      case x :: xs => l2 match {
        case Nil => List(x)
        case y :: ys => x :: y :: merge(xs, ys)
      }
    }

    val numberPattern = "([0-9]+)".r
    // Using raw strings to avoid escaping problems
    val operationPattern = """([+-/*])""".r

    val numbers = numberPattern.findAllIn(s).toList
    val operations = operationPattern.findAllIn(s).toList

    merge(numbers, operations)

  }

}
