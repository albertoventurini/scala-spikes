package arithmeticExpressions



object ExprParser {

  def parse(s: String): Node = {

    case class NodeWithOp(n: Node, o: Option[Operation])

    def buildNodeWithOpList(items: List[String]): List[NodeWithOp] = items match {
      case Nil => throw new Exception("No items")
      case num :: Nil => List(new NodeWithOp(num.toInt, None))
      case num :: op :: rest => new NodeWithOp(num.toInt, Some(op)) :: buildNodeWithOpList(rest)
    }

    def buildTree(items: List[NodeWithOp]): Node = items match {
      case Nil => throw new Exception("No items 2")
      case NodeWithOp(n, o) :: Nil => n
      case NodeWithOp(n, o) :: rest => new OpNode(o.get, buildTree(rest), n)
    }

    def reduceItems(items: List[NodeWithOp], ops: Set[Operation]): List[NodeWithOp] = {
      items match {
        case Nil => Nil
        case NodeWithOp(n, None) :: Nil => List(NodeWithOp(n, None))
        case NodeWithOp(n, Some(o)) :: rest => {
          val (nodesToReduce, tail) = items.span(node => (node.o.isEmpty) || (ops contains node.o.get))

          if(nodesToReduce == Nil)
            new NodeWithOp(n, Some(o)) :: reduceItems(rest, ops)
          else {

            val (newNodesToReduce, newTail) =
              if(tail == Nil)
                (nodesToReduce, tail)
            else
                (nodesToReduce :+ tail.head, tail.tail)

            val nextOp = newNodesToReduce.last.o

            new NodeWithOp(buildTree(newNodesToReduce), nextOp) :: reduceItems(newTail, ops)
          }
        }
      }
    }

    val tokenized = Tokenizer.tokenize(s).reverse
    val nodesWithOp = buildNodeWithOpList(tokenized)
    val reducedMultDiv = reduceItems(nodesWithOp, Set("*", "/"))
    buildTree(reducedMultDiv)

  }

}