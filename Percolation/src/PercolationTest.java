import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

  @Test
  public void testOpen() {
    Percolation p = new Percolation(2);
    assertEquals(false, p.isOpen(1, 1));
    p.open(1,1);
    assertEquals(true, p.isOpen(1, 1));
  }

  public void testIsFull() {
    Percolation p = new Percolation(2);
    assertEquals(false, p.isFull(1, 1));
    assertEquals(false, p.isFull(2, 1));
    p.open(2,1);
    assertEquals(false, p.isFull(2, 1));
    p.open(1,1);
    assertEquals(true, p.isFull(2, 1));
  }
  
  public void testPercolates() {
    Percolation p = new Percolation(2);
    p.open(2,1);
    assertEquals(false, p.percolates());
    p.open(1,1);
    assertEquals(true, p.percolates());
  }
  
  public void testBackwash() {
  	Percolation p = new Percolation(2);
  	p.open(1, 1);
  	p.open(2, 1);
  	p.open(2, 2);
  	assertEquals(true, p.isFull(2, 1));
  	assertEquals(false, p.isFull(2, 2));
  }
}
