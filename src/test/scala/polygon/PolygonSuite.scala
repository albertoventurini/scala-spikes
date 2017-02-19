package polygon

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import Utils._

@RunWith(classOf[JUnitRunner])
class PolygonSuite extends FunSuite {

  test("length of a straight segment") {
    val segment = new Segment(new Point(0, 0), new Point(0, 1))

    assert(segment.length === 1.0)
  }

  test("length of a slanted segment") {
    val segment = new Segment(new Point(0, 0), new Point(1, 1))

    assert(segment.length === Math.sqrt(2.0))
  }

  test("calculate the perimeter correctly") {
    val pol = new Polygon(List( new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0) ))

    val result = pol.perimeter

    assert(result == 4)
  }

  test("implicit instantiation of point") {
    val point: Point = "1 0"
    assert(point.x == 1)
    assert(point.y == 0)
  }

  test("calculate the area correctly") {
    val pol = new Polygon(List( new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 0) ))

    val result = pol.area

    assert(result == 1)
  }

}
