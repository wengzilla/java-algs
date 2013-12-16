import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Test;

public class RandomizedQueueTest {

  @Test
  public void testEmpty() {
    RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
    assertEquals(true, r.isEmpty());
    
    r.enqueue(1);
    assertEquals(false, r.isEmpty());
  }
  
  public void testSize() {
    RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
    assertEquals(0, r.size());
    
    r.enqueue(1);
    assertEquals(1, r.size());
    
    r.enqueue(1);
    assertEquals(2, r.size());
    
    r.dequeue();
    assertEquals(1, r.size());
    
    r.dequeue();
    assertEquals(0, r.size());
  }
  
  @Test
  public void testDequeue() {
    RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();  
    r.enqueue(2);
    r.enqueue(1);
    assertEquals(2, (int) r.dequeue());
    assertEquals(1, (int) r.dequeue());
  }
 
  @Test
  public void testIterator() {
    RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
    r.enqueue(0);
    r.enqueue(1);
    r.enqueue(2);
    
    int[] answers = { 0, 1, 2 };
    for (int number : r)
      assertEquals(number, answers[number]);
  }
  
  @Test(expected = NoSuchElementException.class)
  public void testDequeueOnEmptyRandomizedQueue() {
    RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
    r.dequeue();
  }
}
