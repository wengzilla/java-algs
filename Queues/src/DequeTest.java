import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Test;

public class DequeTest {

  @Test
  public void testEmpty() {
    Deque<Integer> d = new Deque<Integer>();
    assertEquals(true, d.isEmpty());
    
    d.addFirst(1);
    assertEquals(false, d.isEmpty());
    
    d.removeLast();
    assertEquals(true, d.isEmpty());
  }
  
  @Test
  public void testSize() {
    Deque<Integer> d = new Deque<Integer>();
    assertEquals(0, d.size());
    
    d.addFirst(1);
    assertEquals(1, d.size());
    
    d.addLast(1);
    assertEquals(2, d.size());
    
    d.removeLast();
    assertEquals(1, d.size());
    
    d.removeFirst();
    assertEquals(0, d.size());
  }
  
  @Test
  public void testRemoveFirst() {
    Deque<Integer> d = new Deque<Integer>();  
    d.addFirst(2);
    d.addFirst(1);
    assertEquals(1, (int) d.removeFirst());
  }

  @Test
  public void testRemoveLast() {
    Deque<Integer> d = new Deque<Integer>();  
    d.addFirst(2);
    d.addFirst(1);
    assertEquals(2, (int) d.removeLast());
  }
  
  @Test
  public void testAddLast() {
    Deque<Integer> d = new Deque<Integer>();
    d.addLast(1);
    d.addLast(2);
    assertEquals(2, (int) d.removeLast());
    assertEquals(1, (int) d.removeFirst());
  }
  
  @Test
  public void testIterator() {
    Deque<Integer> d = new Deque<Integer>();
    d.addLast(1);
    d.addLast(2);
    d.addFirst(0);
    
    int[] answers = { 0, 1, 2 };
    for (int number : d)
      assertEquals(number, answers[number]);
  }
  
  @Test(expected = NoSuchElementException.class)
  public void testRemoveFirstOnEmptyDeque() {
    Deque<Integer> d = new Deque<Integer>();
    d.removeFirst();
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveLastOnEmptyDeque() {
    Deque<Integer> d = new Deque<Integer>();
    d.removeLast();
  }
}
