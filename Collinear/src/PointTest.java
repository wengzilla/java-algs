import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {
  private static final double DELTA = 1e-15;
  
  @Test
  public void testSlopeTo() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(2, 1);
    assertEquals(0.5, p1.slopeTo(p2), DELTA);
    
    p2 = new Point(0, 0);
    assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2), DELTA);
    
    p2 = new Point(0, 1);
    assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2), DELTA);
    
    p2 = new Point(1, 0);
    assertEquals(0, p1.slopeTo(p2), DELTA);
  }

}
