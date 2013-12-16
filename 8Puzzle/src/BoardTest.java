import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;


public class BoardTest {

  @Test
  public void testBoard() {
    int[][] blocks = {{1,2,3},{4,5,6},{7,8,0}};
    Board board = new Board(blocks);
  }
  
  @Test
  public void testDimension() {
    int[][] blocks = {{1,2,3},{4,5,6},{7,8,0}};
    Board board = new Board(blocks);
    assertEquals(3, board.dimension());
  }
  
  @Test
  public void testHamming() {
    int[][] blocks0 = {{1,2,3},{4,5,6},{7,8,0}};
    Board board0 = new Board(blocks0);
    assertEquals(0, board0.hamming());

    int[][] blocks1 = {{1,2,4},{3,5,6},{7,8,0}};
    Board board1 = new Board(blocks1);
    assertEquals(2, board1.hamming());

    int[][] blocks2 = {{8,1,3},{4,0,2},{7,6,5}};
    Board board2 = new Board(blocks2);
    assertEquals(5, board2.hamming());
  }

  @Test
  public void testManhattan() {
    int[][] blocks0 = {{1,2,3},{4,5,6},{7,8,0}};
    Board board0 = new Board(blocks0);
    assertEquals(0, board0.manhattan());

    int[][] blocks1 = {{1,2,4},{3,5,6},{7,8,0}};
    Board board1 = new Board(blocks1);
    assertEquals(6, board1.manhattan());

    int[][] blocks2 = {{8,1,3},{4,0,2},{7,6,5}};
    Board board2 = new Board(blocks2);
    assertEquals(10, board2.manhattan());
  }

  @Test
  public void testIsGoal() {
    int[][] blocks0 = {{1,2,3},{4,5,6},{7,8,0}};
    Board board0 = new Board(blocks0);
    assertEquals(true, board0.isGoal());

    int[][] blocks1 = {{1,2,4},{3,5,6},{7,8,0}};
    Board board1 = new Board(blocks1);
    assertEquals(false, board1.isGoal());

    int[][] blocks2 = {{8,1,3},{4,0,2},{7,6,5}};
    Board board2 = new Board(blocks2);
    assertEquals(false, board2.isGoal());
  }
  
  @Test
  public void testToString() {
    int[][] blocks0 = {{1,2,3},{4,5,6},{7,8,0}};
    Board board0 = new Board(blocks0);
    assertEquals("1 2 3\n4 5 6\n7 8 0", board0.toString());
  }

  @Test
  public void testEquals() {
    int[][] blocks0 = {{1,2,3},{4,5,6},{7,8,0}};
    Board board0 = new Board(blocks0);
    Board board1 = new Board(blocks0);
    assertEquals(true, board0.equals(board1));
    assertEquals(false, board0.equals(board0.twin()));
  }
  
  @Test
  public void testExpectedPosition() { 
    int[][] blocks0 = {{1,2,3},{4,5,6},{7,8,0}};
    Board board0 = new Board(blocks0);
    Method method;

    try {
      method = board0.getClass().getDeclaredMethod("expectedPosition", int.class);
      method.setAccessible(true);
      assertArrayEquals(new int[]{0,0}, (int[]) method.invoke(board0, 1));
      assertArrayEquals(new int[]{1,1}, (int[]) method.invoke(board0, 5));
      assertArrayEquals(new int[]{2,1}, (int[]) method.invoke(board0, 8));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
}
