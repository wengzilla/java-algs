public class Subset {
  public static void main(String[] args) {
    int printCount = Integer.parseInt(args[0]);

    RandomizedQueue<String> strings = new RandomizedQueue<String>();

    while (!StdIn.isEmpty()) {
      strings.enqueue(StdIn.readString());
    }
    
    for (int i = 0; i < printCount; i++) {
      System.out.println(strings.dequeue());
    }
  }
}