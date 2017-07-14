
public class DynamicProg {

  public int numberOfPaths(int m, int n) {
    if (m == 1 || n == 1) {
      return 1;
    }
    int temp = numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1);
    return temp;

  }

  public int uniquePathsBT2(int m, int n) {
    return dfs(0, 0, m, n);
  }

  public int dfs(int i, int j, int m, int n) {
    if (i == m - 1 && j == n - 1) {
      return 1;
    }

    if (i < m - 1 && j < n - 1) {
      return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }

    if (i < m - 1) {
      return dfs(i + 1, j, m, n);
    }

    if (j < n - 1) {
      return dfs(i, j + 1, m, n);
    }

    return 0;
  }

  public int uniquePaths(int m, int n) {
    if (m == 0 || n == 0)
      return 0;
    if (m == 1 || n == 1)
      return 1;

    int[][] dp = new int[m][n];

    // left column
    for (int i = 0; i < m; i++) {
      dp [i] [0] = 1;
    }

    // top row
    for (int j = 0; j < n; j++) {
      dp [0] [j] = 1;
    }

    // fill up the dp table
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp [i] [j] = dp [i - 1] [j] + dp [i] [j - 1];
      }
    }

    return dp [m - 1] [n - 1];
  }

  // 0 0 0
  // 0 1 0
  // 0 0 0
  // obstacle at 1
  // uinque paths 2
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid == null || obstacleGrid.length == 0)
      return 0;

    int m = obstacleGrid.length;
    int n = obstacleGrid [0].length;

    if (obstacleGrid [0] [0] == 1 || obstacleGrid [m - 1] [n - 1] == 1)
      return 0;

    int[][] dp = new int[m][n];
    dp [0] [0] = 1;

    // left column
    for (int i = 1; i < m; i++) {
      if (obstacleGrid [i] [0] == 1) {
        dp [i] [0] = 0;
      } else {
        dp [i] [0] = dp [i - 1] [0];
      }
    }

    // top row
    for (int i = 1; i < n; i++) {
      if (obstacleGrid [0] [i] == 1) {
        dp [0] [i] = 0;
      } else {
        dp [0] [i] = dp [0] [i - 1];
      }
    }

    // fill up cells inside
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid [i] [j] == 1) {
          dp [i] [j] = 0;
        } else {
          dp [i] [j] = dp [i - 1] [j] + dp [i] [j - 1];
        }

      }
    }

    return dp [m - 1] [n - 1];
  }

  public int uniquePathsPractise(int[][] grid) {

    if (grid == null || grid.length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid [0].length;

    int[][] dp = new int[m][n];
    // for obstacle initialize dp[0][0] to 1

    // initialize left column
    for (int i = 0; i < m; i++) {
      dp [i] [0] = 1;
    }

    /// for obstacle initialize as follows
    // initialize left column
    // for (int i = 1; i < m; i++) {
    // dp [i] [0] = (grid[i][0] == 1)? 1: dp[i-1][0];
    // }

    // initialize left column
    for (int j = 0; j < n; j++) {
      dp [0] [j] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp [i] [j] = dp [i - 1] [j] + dp [i] [j - 1];
      }

    }

    return dp [m - 1] [n - 1];

  }

  public void minPathSumv2(int[][] grid) {
    // initialize left col

    int m = grid.length;
    int n = grid [0].length;

  }

  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0)
      return 0;

    int m = grid.length;
    int n = grid [0].length;

    int[][] dp = new int[m][n];
    dp [0] [0] = grid [0] [0];

    // initialize top row
    for (int i = 1; i < n; i++) {
      dp [0] [i] = dp [0] [i - 1] + grid [0] [i];
    }

    // initialize left column
    for (int j = 1; j < m; j++) {
      dp [j] [0] = dp [j - 1] [0] + grid [j] [0];
    }

    // fill up the dp table
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (dp [i - 1] [j] > dp [i] [j - 1]) {
          dp [i] [j] = dp [i] [j - 1] + grid [i] [j];
        } else {
          dp [i] [j] = dp [i - 1] [j] + grid [i] [j];
        }
      }
    }

    return dp [m - 1] [n - 1];
  }

  public static void main(String[] args) {
    DynamicProg dp = new DynamicProg();
    System.out.println(" Unique Paths: " + dp.uniquePaths(4, 4));

    System.out.println("unique paths DFS top down  :" + dp.uniquePathsBT2(4, 4));

    System.out.println("unique paths DFS bottom up :" + dp.numberOfPaths(4, 4));
  }
}