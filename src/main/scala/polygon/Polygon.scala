package polygon

object Utils {
  implicit def stringToPoint(s: String): Point = {
    val numbers = s.split(" ").map(_.toInt)
    new Point(numbers(0), numbers(1))
  }
}

class Point(val x: Int, val y: Int)

object Point {
  def fromString(s: String): Point = {
    val numbers = s.split(" ").map(_.toInt)
    new Point(numbers(0), numbers(1))
  }
}


class Segment(p1: Point, p2: Point) {

  def length: Double = Math.sqrt( Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) )

}

class Polygon(points: List[Point]) {

  private def segments: List[Segment] =
    points zip (points.drop(1) :+ points.head) map { case (p1, p2) => new Segment(p1, p2) }

  lazy val perimeter =
    segments.map(_.length).sum

  lazy val area = {
    val mult = points zip (points.drop(1) :+ points.head) map { case(p1, p2) => p1.x*p2.y - p2.x*p1.y }
    Math.abs(mult.sum) / 2.0
  }

}
