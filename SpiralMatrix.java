
public class SpiralMatrix {

  public static void printSpiral(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return;
    }
    int m = matrix.length;
    int n = matrix [0].length;
    // define boundaries of untraversed array
    int L = 0, R = n - 1, T = 0, B = m - 1;
    // define direction which can only have 4 values
    int direction = 0;

    while (L <= R && T <= R) {
      switch (direction) {
        // L TO R in T row
        case 0:
          for (int i = L; i <= R; i++) {
            System.out.print(matrix [T] [i]);

          }
          // peel off top row since we are done printing
          // untraversed boundry changes
          T++;
          break;
        // T TO B in R col
        case 1:
          for (int i = T; i <= B; i++) {
            System.out.print(matrix [i] [R]);

          }
          // peel off top row since we are done printing
          // untraversed boundry changes
          R--;
          break;
        // R TO L in B row
        case 2:
          for (int i = R; i >= L; i--) {
            System.out.print(matrix [B] [i]);

          }
          // peel off top row since we are done printing
          // untraversed boundry changes
          B--;
          break;
        // B TO T in L col
        case 3:
          for (int i = B; i >= T; i--) {
            System.out.print(matrix [i] [L]);

          }
          // peel off top row since we are done printing
          // untraversed boundry changes
          L++;
          break;
      }
      direction = (direction + 1) % 4;

    }

  }

  public static void main(String args[]) {
    int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    printSpiral(matrix);

    System.out.println();
    printSpiral(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } });
  }

}
