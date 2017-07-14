
public class MathProbs {

  public static double power(double x, int n) {
    if (n == 0)
      return 1;

    double v = power(x, n / 2);

    if (n % 2 == 0) {
      return v * v;
    } else {
      return v * v * x;
    }
  }

  public static int sqrt(int x) {
    if (x < 0) {
      return -1;
    }

    // in case of x==1
    long high = x / 2 + 1;
    long low = 0;
    System.out.println("high:" + high);
    System.out.println("low:" + low);

    while (low <= high) {

      long mid = low + (high - low) / 2;
      System.out.println("high:" + high);
      System.out.println("low:" + low);
      System.out.println("mid:" + mid);

      long sq = mid * mid;

      if (sq == x) {
        return (int) mid;
      } else if (sq < x) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    System.out.println("returning:" + high);
    return (int) high;
  }

  public static void main(String[] args) {
    System.out.println(" SQRT is: " + sqrt(17));
    // System.out.println(" SQRT is: " + sqrt(25));
    System.out.println(power(2, 3));
  }

}
