public class Percolation {
  private boolean[][] mOpen;
  private int mSize;
  private WeightedQuickUnionUF mUFGrid;
  private WeightedQuickUnionUF mUFGridFilled;
  
  public Percolation(int N) {
    // create N-by-N grid, with all sites blocked
    mSize = N;
    mOpen = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        mOpen[i][j] = false;
      }
    }
    
    mUFGrid = new WeightedQuickUnionUF(N * N + 2); // Add 1 for the invisible top vertex and 1 for the invisible bottom vertex.
    mUFGridFilled = new WeightedQuickUnionUF(N * N + 2);
  }
  
  public void open(int row, int col) {
    // open site (row i, column j) if it is not already
    if (!isValidCoordinate(row, col)) throw new java.lang.IndexOutOfBoundsException();
    mOpen[row-1][col-1] = true;
    
    if (isValidCoordinate(row - 1, col) && isOpen(row - 1, col)) {
      mUFGrid.union(coordinateToValue(row, col), coordinateToValue(row - 1, col));
      mUFGridFilled.union(coordinateToValue(row, col), coordinateToValue(row - 1, col));
    }
    if (isValidCoordinate(row + 1, col) && isOpen(row + 1, col)) {
      mUFGrid.union(coordinateToValue(row, col), coordinateToValue(row + 1, col));
      mUFGridFilled.union(coordinateToValue(row, col), coordinateToValue(row + 1, col));
    }
    if (isValidCoordinate(row, col - 1) && isOpen(row, col - 1)) {
      mUFGrid.union(coordinateToValue(row, col), coordinateToValue(row, col - 1));
      mUFGridFilled.union(coordinateToValue(row, col), coordinateToValue(row, col - 1));
    }
    if (isValidCoordinate(row, col + 1) && isOpen(row, col + 1)) {
      mUFGrid.union(coordinateToValue(row, col), coordinateToValue(row, col + 1));
      mUFGridFilled.union(coordinateToValue(row, col), coordinateToValue(row, col + 1));
    }
    
    if (isFirstRow(row, col)) {
      mUFGrid.union(topVertexValue(), coordinateToValue(row, col));
      mUFGridFilled.union(topVertexValue(), coordinateToValue(row, col));
    }

    if (isLastRow(row, col)) mUFGrid.union(bottomVertexValue(), coordinateToValue(row, col));
  }
  
  public boolean isOpen(int row, int col) {
    // is site (row i, column j) open?
    if (!isValidCoordinate(row, col)) throw new java.lang.IndexOutOfBoundsException();
    return mOpen[row-1][col-1];
  }

  public boolean isFull(int row, int col) {
    // is site (row i, column j) full?
    if (!isValidCoordinate(row, col)) throw new java.lang.IndexOutOfBoundsException();
    return mUFGridFilled.connected(topVertexValue(), coordinateToValue(row, col));
  }

  public boolean percolates() {
    // does the system percolate?
    return mUFGrid.connected(topVertexValue(), bottomVertexValue());
  }
  
  private boolean isFirstRow(int row, int col) {
    return row == 1;
  }
  
  private boolean isLastRow(int row, int col) {
    return row == mSize;
  }

  private boolean isValidCoordinate(int row, int col) {
    return row <= mSize && row >= 1 && col <= mSize && col >= 1;
  }
  
  private int coordinateToValue(int row, int col) {
    return mSize * (row - 1) + (col - 1);
  }
  
  private int topVertexValue() {
    return mSize * mSize;
  }

  private int bottomVertexValue() {
    return topVertexValue() + 1;
  }
}