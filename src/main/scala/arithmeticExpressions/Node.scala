package arithmeticExpressions


trait Node {
  def eval: Int
}

object Node {
  implicit def intToLeafNode(value: Int): Node = {
    new LeafNode(value)
  }
}

trait Operation
case object Addition extends Operation
case object Subtraction extends Operation
case object Multiplication extends Operation
case object Division extends Operation

object Operation {
  implicit def stringToOperation(s: String): Operation = s match {
    case "+" => Addition
    case "-" => Subtraction
    case "*" => Multiplication
    case "/" => Division
  }
}

case class OpNode (op: Operation, left: Node, right: Node) extends Node {

  val opFun = (x: Int, y: Int) => op match {
    case Addition => x+y
    case Subtraction => x-y
    case Multiplication => x*y
    case Division => x/y
  }

  override def eval = opFun(left.eval, right.eval)

}

case class LeafNode(value: Int) extends Node {
  override def eval = value
}

