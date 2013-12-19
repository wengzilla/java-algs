import java.util.LinkedList;

public class Board {
  private final int[][] mBoard;

  public Board(int[][] blocks) {
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    
    // make deep copy so that mBoard doesn't change if board changes...
    mBoard = new int[blocks[0].length][blocks[0].length];

    for (int i = 0; i < blocks.length; i++) {
      System.arraycopy(blocks[i], 0, mBoard[i], 0, blocks[0].length);
    }
  }

  public int dimension() {
    // board dimension N
    return mBoard.length;
  }

  public int hamming() {
    // number of blocks out of place
    int currentNumber = 0;
    int hammingCount = 0;

    for (int y = 0; y < dimension(); y++) {
      for (int x = 0; x < dimension(); x++) {
        // skip the last box, for the 0.
        if (y == (dimension() - 1) && x == (dimension() - 1))
          continue;
        else {
          currentNumber = x + y * dimension() + 1;
          // System.out.println(y + " " + x + " " + current_number);
          if (currentNumber != mBoard[y][x]) {
            hammingCount++;
            // System.out.println(hamming_count + " " + current_number);
          }
        }
      }
    }

    return hammingCount;
  }

  public int manhattan() {
    // sum of Manhattan distances between blocks and goal
    int distance;
    int manhattanCount = 0;

    for (int y = 0; y < dimension(); y++) {
      for (int x = 0; x < dimension(); x++) {
        // skip the 0 box.
        if (mBoard[y][x] == 0)
          continue;
        else {
          // System.out.println(y + " " + x + " " + current_number);
          manhattanCount += expectedDistance(y, x);
          // System.out.println(hamming_count + " " + current_number);
        }
      }
    }

    return manhattanCount;
  }

  private int expectedDistance(int y, int x) {
    int expectedX;
    int expectedY;

    expectedX = expectedPosition(mBoard[y][x])[1];
    expectedY = expectedPosition(mBoard[y][x])[0];

    return Math.abs(expectedX - x) + Math.abs(expectedY - y);
  }

  private int[] expectedPosition(int n) {
    int[] position = { (n - 1) / dimension(), (n - 1) % dimension() };
    return position;
  }

  public boolean isGoal() {
    // is this board the goal board?
    return hamming() == 0;
  }

  public Board twin() {
    for (int y = 0; y < dimension(); y++) {
      for (int x = 0; x < dimension() - 1; x++) {
        if (mBoard[y][x] != 0 && mBoard[y][x + 1] != 0) {
          return swap(y, x + 1, y, x);
        }
      }
    }

    return null;
  }

  public boolean equals(Object x) {
    // does this board equal y?
    // a board obtained by exchanging two adjacent blocks in the same row
    if (x == this)
      return true;
    if (x == null)
      return false;
    if (x.getClass() != this.getClass())
      return false;

    return toString().equals(x.toString());
  }

  public Iterable<Board> neighbors() {
    // all neighboring boards
    LinkedList<Board> list = new LinkedList<Board>();
    int[] emptyPosition = emptyPosition();
    int emptyPositionY = emptyPosition[0];
    int emptyPositionX = emptyPosition[1];

    // System.out.println(emptyPositionY + " " + emptyPositionX);

    if (emptyPositionY - 1 >= 0) {
      list.add(swap(emptyPositionY, emptyPositionX, emptyPositionY - 1,
          emptyPositionX));
    }
    if (emptyPositionY + 1 < dimension()) {
      list.add(swap(emptyPositionY, emptyPositionX, emptyPositionY + 1,
          emptyPositionX));
    }
    if (emptyPositionX - 1 >= 0) {
      list.add(swap(emptyPositionY, emptyPositionX, emptyPositionY,
          emptyPositionX - 1));
    }
    if (emptyPositionX + 1 < dimension()) {
      list.add(swap(emptyPositionY, emptyPositionX, emptyPositionY,
          emptyPositionX + 1));
    }

    return list;
  }

  private int[] emptyPosition() {
    for (int y = 0; y < dimension(); y++) {
      for (int x = 0; x < dimension(); x++) {
        if (mBoard[y][x] == 0) {
          return (new int[] { y, x });
        }
      }
    }
    return null;
  }

  private Board swap(int y1, int x1, int y2, int x2) {
    int[][] board = new int[dimension()][dimension()];

    for (int i = 0; i < board.length; i++) {
      System.arraycopy(mBoard[i], 0, board[i], 0, mBoard[0].length);
    }

    board[y1][x1] = mBoard[y2][x2];
    board[y2][x2] = mBoard[y1][x1];

    return new Board(board);
  }

  public String toString() {
    // string representation of the board (in the output format specified below)
    String board = dimension() + "\n";

    for (int y = 0; y < dimension(); y++) {
      for (int x = 0; x < dimension(); x++) {
        board += mBoard[y][x];
        if (x == dimension() - 1) board += "";
        else board += " ";
      }
      if (y == dimension() - 1) board += "";
      else board += "\n";
    }

    return board;
  }
}