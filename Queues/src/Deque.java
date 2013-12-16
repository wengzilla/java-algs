import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node mHead;
  private Node mTail;

  public Deque() {
    // construct an empty deque
    mHead = null;
    mTail = null;
  }

  public boolean isEmpty() {
    // is the deque empty?
    return mHead == null;
  }

  public int size() {
    // return the number of items on the deque
    int count = 0;
    for (Item x : this)
      count++;
    return count;
  }

  public void addFirst(Item item) {
    // insert the item at the front
    if (item == null) throw new NullPointerException();
    Node oldHead = mHead;
    mHead = new Node(item, oldHead, null);
    if (oldHead != null) oldHead.last = mHead;
    else mTail = mHead;
  }

  public void addLast(Item item) {
    // insert the item at the end
    if (item == null) throw new NullPointerException();
    Node oldTail = mTail;
    mTail = new Node(item, null, oldTail);
    if (oldTail != null) oldTail.next = mTail;
    else mHead = mTail;
  }

  public Item removeFirst() {
    // delete and return the item at the front
    if (mHead == null) throw new NoSuchElementException();
    Node oldHead = mHead;
    mHead = oldHead.next;
    if (mHead != null) mHead.last = null;
    else mTail = null;
    return oldHead.item;
  }

  public Item removeLast() {
    // delete and return the item at the end
    if (mTail == null) throw new NoSuchElementException();
    Node oldTail = mTail;
    mTail = oldTail.last;
    if (mTail != null) mTail.next = null;
    else mHead = null;
    return oldTail.item;
  }

  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {
    private Node current = mHead;

    @Override
    public boolean hasNext() {
      // TODO Auto-generated method stub
      return current != null;
    }

    @Override
    public Item next() {
      // TODO Auto-generated method stub
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class Node {
    private Item item;
    private Node next;
    private Node last;

    public Node(Item item, Node next, Node last) {
      this.item = item;
      this.next = next;
      this.last = last;
    }
  }
}