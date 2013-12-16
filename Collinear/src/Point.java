import java.util.Comparator;

public class Point implements Comparable<Point> {

  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER = new PointComparator(); 
  private final int x; // x coordinate
  private final int y; // y coordinate

  // create the point (x, y)
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  // plot this point to standard drawing
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  // draw line between this point and that point to standard drawing
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  // slope between this point and that point
  public double slopeTo(Point that) {
    if (that == null)
      throw new java.lang.NullPointerException();

    if (this.y == that.y && this.x == that.x) return Double.NEGATIVE_INFINITY;
    else if (isVertical(that)) return Double.POSITIVE_INFINITY;
    else if (isHorizontal(that)) return 0.0;
    else return 1.0 * (that.y - this.y) / (that.x - this.x);
  }

  // is this point lexicographically smaller than that one?
  // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    if (that == null) throw new java.lang.NullPointerException();

    if (this.y > that.y)
      return 1;
    else if (this.y == that.y && this.x > that.x)
      return 1;
    else if (this.y == that.y && this.x == that.x)
      return 0;
    else
      return -1; // (this.y < that.y OR this.y == that.y && this.x < that.x)
  }

  // return string representation of this point
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  private boolean isVertical(Point that) {
    if (that == null)
      throw new java.lang.NullPointerException();

    return that.y != this.y && that.x == this.x;
  }

  private boolean isHorizontal(Point that) {
    if (that == null)
      throw new java.lang.NullPointerException();

    return that.x != this.x && that.y == this.y;
  }

  // unit test
  public static void main(String[] args) {
    /* YOUR CODE HERE */
  }

  private class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point arg0, Point arg1) {
      if (arg0 == null || arg1 == null) throw new java.lang.NullPointerException();
      // TODO Auto-generated method stub
      double slopeZero = Point.this.slopeTo(arg0);
      double slopeOne = Point.this.slopeTo(arg1);

      if (slopeZero > slopeOne) return 1;
      else if (slopeZero == slopeOne) return 0;
      else return -1;
    }
  }
}