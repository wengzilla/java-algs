import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.LinkedList;

import org.junit.Test;

public class BoardTest {

  @Test
  public void testBoard() {
    int[][] blocks = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board = new Board(blocks);
  }

  @Test
  public void testDimension() {
    int[][] blocks = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board = new Board(blocks);
    assertEquals(3, board.dimension());
  }

  @Test
  public void testHamming() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    assertEquals(0, board0.hamming());

    int[][] blocks1 = { { 1, 2, 4 }, { 3, 5, 6 }, { 7, 8, 0 } };
    Board board1 = new Board(blocks1);
    assertEquals(2, board1.hamming());

    int[][] blocks2 = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
    Board board2 = new Board(blocks2);
    assertEquals(5, board2.hamming());
  }

  @Test
  public void testManhattan() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    assertEquals(0, board0.manhattan());

    int[][] blocks1 = { { 1, 2, 4 }, { 3, 5, 6 }, { 7, 8, 0 } };
    Board board1 = new Board(blocks1);
    assertEquals(6, board1.manhattan());

    int[][] blocks2 = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
    Board board2 = new Board(blocks2);
    assertEquals(10, board2.manhattan());

    int[][] blocks3 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
    Board board3 = new Board(blocks3);
    assertEquals(0, board3.manhattan());

    int[][] blocks4 = { { 1, 0 }, { 2, 3 } };
    Board board4 = new Board(blocks4);
    assertEquals(3, board4.manhattan());
  }

  @Test
  public void testIsGoal() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    assertEquals(true, board0.isGoal());

    int[][] blocks1 = { { 1, 2, 4 }, { 3, 5, 6 }, { 7, 8, 0 } };
    Board board1 = new Board(blocks1);
    assertEquals(false, board1.isGoal());

    int[][] blocks2 = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
    Board board2 = new Board(blocks2);
    assertEquals(false, board2.isGoal());
  }

  @Test
  public void testToString() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    assertEquals("3\n1 2 3\n4 5 6\n7 8 0", board0.toString());
  }

  @Test
  public void testEquals() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    Board board1 = new Board(blocks0);
    assertEquals(true, board0.equals(board1));
    assertEquals(false, board0.equals(board0.twin()));
  }

  @Test
  public void testTwin() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    int[][] blocks1 = { { 2, 1, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board1 = new Board(blocks1);
    assertEquals(true, board0.twin().equals(board1));
  }

  @Test
  public void testNeighbors() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    LinkedList<Board> neighbors = (LinkedList) board0.neighbors();

    int[][] blocksSolution1 = { { 1, 2, 3 }, { 4, 5, 0 }, { 7, 8, 6 } };
    assertEquals(true, neighbors.pop().equals(new Board(blocksSolution1)));

    int[][] blocksSolution2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } };
    assertEquals(true, neighbors.pop().equals(new Board(blocksSolution2)));

  }

//  @Test
//  public void testToString() {
//    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
//    Board board0 = new Board(blocks0);
//    LinkedList<Board> neighbors = (LinkedList) board0.neighbors();
//  }
//  
  @Test
  public void testExpectedPosition() {
    int[][] blocks0 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    Board board0 = new Board(blocks0);
    Method method;

    try {
      method = board0.getClass().getDeclaredMethod("expectedPosition",
          int.class);
      method.setAccessible(true);
      assertArrayEquals(new int[] { 0, 0 }, (int[]) method.invoke(board0, 1));
      assertArrayEquals(new int[] { 1, 1 }, (int[]) method.invoke(board0, 5));
      assertArrayEquals(new int[] { 2, 1 }, (int[]) method.invoke(board0, 8));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
