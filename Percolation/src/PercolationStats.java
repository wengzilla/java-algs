public class PercolationStats {
  private int mTrials;
  private int mSize;
  private double[] mResults;

  public PercolationStats(int N, int T) {
    // perform T independent computational experiments on an N-by-N grid
    mSize = N;
    mTrials = T;
    mResults = new double[mTrials];

    if (N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException();
    
    for (int t = 0; t < mTrials; t++) {
      Percolation trial = new Percolation(mSize);

      while (!trial.percolates()) {
        trial.open(StdRandom.uniform(N) + 1, StdRandom.uniform(N) + 1);
      }

      mResults[t] = openSites(trial) / (mSize * mSize * 1.0);
    }
  }
  
  private int openSites(Percolation trial) {
    int count = 0;
    for (int i = 1; i <= mSize; i++) {
      for (int j = 1; j <= mSize; j++) {
        if (trial.isOpen(i, j)) count++;
      }
    }
    return count;
  }

  public double mean() {
    // sample mean of percolation threshold
    double sum = 0;

    for (int i = 0; i < mTrials; i++) {
      sum += mResults[i]; 
    }

    return sum / mTrials;
  }

  public double stddev() {
    // sample standard deviation of percolation threshold
    double sum = 0;

    for (int i = 0; i < mTrials; i++) {
      sum += (mResults[i] - mean()) * (mResults[i] - mean()); 
    }

    return Math.sqrt(sum / (mTrials - 1));
  }

  public double confidenceLo() {             
    // returns lower bound of the 95% confidence interval
    return mean() - 1.96 * stddev() / Math.sqrt(mTrials);
  }

  public double confidenceHi() {
    // returns upper bound of the 95% confidence interval
    return mean() + 1.96 * stddev() / Math.sqrt(mTrials);
  }

  public static void main(String[] args) {
    // test client, described below
    int size = Integer.parseInt(args[0]); // parse int
    int trials = Integer.parseInt(args[1]); // parse int
    
    PercolationStats ps = new PercolationStats(size, trials);
    System.out.println("mean = " + ps.mean());
    System.out.println("stddev = " + ps.stddev());
    System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
  }

}
