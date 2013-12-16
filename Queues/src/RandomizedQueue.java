import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] mQueue;
  private int mTail = 0;
  private final static int INITIAL_QUEUE_SIZE = 4;

  public RandomizedQueue() {
    // construct an empty randomized queue
    mQueue = (Item[]) new Object[INITIAL_QUEUE_SIZE];
  }

  public boolean isEmpty() {
    // is the queue empty?
    return mTail == 0; 
  }

  public int size() {
    // return the number of items on the queue
    return mTail;
  }

  public void enqueue(Item item) {
    // add the item
    if (item == null) throw new NullPointerException();
    
    if (mQueue.length - 1 == mTail) resize(mQueue.length * 2);
    mQueue[mTail] = item;
    mTail++;
  }

  public Item dequeue() {
    // delete and return a random item
    if (isEmpty()) throw new NoSuchElementException();

    int randomIndex = randomIndex();
    Item item = mQueue[randomIndex];
    mQueue[randomIndex] = mQueue[mTail - 1];
    mQueue[mTail - 1] = null;
    mTail--;

    if (mTail < mQueue.length / 4) resize(mQueue.length / 2);

    return item;
  }

  public Item sample() {
    // return (but do not delete) a random item
    if (isEmpty()) throw new NoSuchElementException();
    return mQueue[randomIndex()];
  }
  public Iterator<Item> iterator() {
    // return an independent iterator over items in random order
    return new RandomizedQueueIterator();
  }

  private int randomIndex() {
    return (int) Math.floor(Math.random() * mTail);
  }

  private void resize(int capacity) {
    Item[] newQueue = (Item[]) new Object[capacity];
    for (int i = 0; i < mTail; i++) {
      newQueue[i] = mQueue[i];
    }
    mQueue = newQueue;
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private Integer[] randomizedIndices;
    private int current = 0;

    public RandomizedQueueIterator() {
      randomizedIndices = new Integer[mTail];
      for (int i = 0; i < mTail; i++) {
        randomizedIndices[i] = i;
      }

      Collections.shuffle(Arrays.asList(randomizedIndices));
    }

    @Override
    public boolean hasNext() {
      // TODO Auto-generated method stub
      return mQueue[current] != null;
    }

    @Override
    public Item next() {
      // TODO Auto-generated method stub
      if (!hasNext()) throw new NoSuchElementException();

      Item item = mQueue[randomizedIndices[current]];
      current++;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
