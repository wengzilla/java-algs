import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


public class PointSETTest {

  @Test
  public void testIsEmpty() {
    PointSET pSet = new PointSET();
    assertEquals(true, pSet.isEmpty());
    pSet.insert(new Point2D(0.5, 0.5));
    assertEquals(false, pSet.isEmpty());
  }

  @Test
  public void testContains() {
    PointSET pSet = new PointSET();
    Point2D point = new Point2D(0.5, 0.5);
    assertEquals(false, pSet.contains(point));
    pSet.insert(point);
    assertEquals(true, pSet.contains(point));
  }
  
  @Test
  public void testRange() {
    PointSET pSet = new PointSET();
    pSet.insert(new Point2D(0.25, 0.25));
    pSet.insert(new Point2D(0.75, 0.75));
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    for(Point2D point : pSet.range(new RectHV(0,0,0.5,0.5))) {
      points.add(point);
    }
    assertEquals(1, points.size());
    assertEquals(0.25, points.get(0).x(), 0.01);
    
    points = new ArrayList<Point2D>();
    for(Point2D point : pSet.range(new RectHV(0,0,1,1))) {
      points.add(point);
    }
    assertEquals(2, points.size());
  }

  @Test
  public void testNearest() {
    PointSET pSet = new PointSET();
    pSet.insert(new Point2D(0.25, 0.25));
    pSet.insert(new Point2D(0.75, 0.75));

    assertEquals(0.25, pSet.nearest(new Point2D(0.30, 0.30)).x(), 0.01);
    assertEquals(0.75, pSet.nearest(new Point2D(0.80, 0.80)).x(), 0.01);
  }
  
}
