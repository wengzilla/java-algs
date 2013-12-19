import java.util.Comparator;

public class Solver {
  private MinPQ<Node> initialPQ;
  private MinPQ<Node> initialTwinPQ;
  private Node currentNode;
  private Node currentTwinNode;
  private Stack<Board> solution = new Stack<Board>();

  public Solver(Board initial) {
    // find a solution to the initial board (using the A* algorithm)
    Node initialNode = new Node(initial, 0, null);
    Node initialTwinNode = new Node(initial.twin(), 0, null);
    initialPQ = new MinPQ<Node>(1, initialNode.MANHATTAN);
    initialTwinPQ = new MinPQ<Node>(1, initialNode.MANHATTAN);

    initialPQ.insert(initialNode);
    initialTwinPQ.insert(initialTwinNode);

    currentNode = initialPQ.delMin();
    currentTwinNode = initialTwinPQ.delMin();

    while (!currentNode.mBoard.isGoal() && !currentTwinNode.mBoard.isGoal()) {
      for (Board board : currentNode.mBoard.neighbors()) {
        if (currentNode.mPrev == null || !board.equals(currentNode.mPrev.mBoard)) {
          initialPQ.insert(new Node(board, currentNode.mMoves + 1, currentNode));
        }
      }
//
//      try {
//        System.out.println("---");
//        Thread.sleep(1000);
//      } catch (InterruptedException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
      
      for (Board board : currentTwinNode.mBoard.neighbors()) {
//        System.out.println("POSSIBILITIES: \n" + board);
        if (currentTwinNode.mPrev == null || !board.equals(currentTwinNode.mPrev.mBoard)) {
          initialTwinPQ.insert(new Node(board, currentTwinNode.mMoves + 1,
              currentTwinNode));
        }
      }

      currentNode = initialPQ.delMin();
      currentTwinNode = initialTwinPQ.delMin();
    }
  }

  public boolean isSolvable() {
    return currentNode.mBoard.isGoal();
  }

  public int moves() {
    // min number of moves to solve initial board; -1 if no solution
    
    if (isSolvable()) {
      return ((Stack<Board>) solution()).size() - 1;
    } else {
      return -1;
    }
  }

  public Iterable<Board> solution() {
    // sequence of boards in a shortest solution; null if no solution
    if (!solution.isEmpty()) return solution;

    Node current = currentNode;

    if (isSolvable()) {
      do {
        solution.push(current.mBoard);
        current = current.mPrev;
      } while (current != null);
    } else {
      return null;
    }

    return solution;
  }

  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) {
        StdOut.println(board);
      }
    }
  }

  private class Node {
    private int mMoves;
    private Board mBoard;
    private Node mPrev;
    private final Comparator<Node> MANHATTAN = new ManhattanComparator();
    private final Comparator<Node> HAMMING = new HammingComparator();

//    public int getMoves() {
//      return mMoves;
//    }
//    
//    public Board getBoard() {
//      return mBoard;
//    }
//    
//    public Node getPrev() {
//      return mPrev;
//    }
//    
//    public Comparator<Node> getManhattan() {
//      return MANHATTAN;
//    }
//
//    public Comparator<Node> getHamming() {
//      return HAMMING;
//    }
    
    public Node(Board board, int moves, Node prev) {
      this.mMoves = moves;
      this.mBoard = board;
      this.mPrev = prev;
    }
  }

  private class ManhattanComparator implements Comparator<Node> {
    @Override
    public int compare(Node arg0, Node arg1) {
      if (arg0 == null || arg1 == null)
        throw new java.lang.NullPointerException();
      // TODO Auto-generated method stub
      double priorityZero = arg0.mBoard.manhattan() + arg0.mMoves;
      double priorityOne = arg1.mBoard.manhattan() + arg1.mMoves;

      if (priorityZero > priorityOne)
        return 1;
      else if (priorityZero == priorityOne)
        return 0;
      else
        return -1;
    }
  }

  private class HammingComparator implements Comparator<Node> {
    @Override
    public int compare(Node arg0, Node arg1) {
      if (arg0 == null || arg1 == null)
        throw new java.lang.NullPointerException();
      // TODO Auto-generated method stub
      double priorityZero = arg0.mBoard.hamming() + arg0.mMoves;
      double priorityOne = arg1.mBoard.hamming() + arg1.mMoves;

      if (priorityZero > priorityOne)
        return 1;
      else if (priorityZero == priorityOne)
        return 0;
      else
        return -1;
    }
  }
}