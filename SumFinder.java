import java.util.Arrays;

public class SumFinder {

  public static void main(String[] args) {
    System.out.println(" Sum exists :" + findIfSumExists(new int[] { 1, 1, 1, 2, 3, 4, 5, 7, 8, 10 }, 11, 4));
    System.out.println(" Sum exists :" + findIfSumExists(new int[] { 1, 2, 3, 4 }, 8, 2));// false
    System.out.println(" Sum exists :" + findIfSumExists(new int[] { 1, 1, 1, 2, 3, 4, 5, 7, 8, 10 }, 11, 6));
    System.out.println(" Sum exists :" + findIfSumExists(new int[] { 1, 1, 2, 3, 4, 5 }, 13, 5));
    System.out.println(" Sum exists :" + findIfSumExists(new int[] { 1, 1, 2, 3, 4, 5 }, 12, 3));

  }

  // 1,1,2,3,4,5,6,8,10
  // Z = 6, N=11
  // N and Z ko rename kar like N -> sum and Z -> numElemsInArr

  public static boolean findIfSumExists(int[] arr, int N, int Z) {
    if (arr.length < 2 || Z > arr.length) {
      return false;
    }
    int arrSum = 0;
    // for example numOfElemsInSum =3 and arr length is 3 then add up all elements most simple case
    if (arr.length == Z) {
      for (int i = 0; i < arr.length; i++) {
        arrSum = arr [i] + arrSum;
      }
      // true or false jo bhi hain
      return(N == arrSum);
    }
    // sort this array
    Arrays.sort(arr);
    boolean sumExists = false;
    for (int i = arr.length - 1; i > 0 && !sumExists; i--) {
      int a = arr [i];
      // initial arr {1,1,1,2,3,4,5,7,8,10}
      // initial Z = 4, N=11
      // at element 5 in the arr -> a = 6, Z= 4 , newArrSum = 5
      // new N and Z
      int remainderSum = N - a;
      int newNumOfElemsInSum = Z - 1;

      // do this until z until 1
      if (remainderSum > 0 && newNumOfElemsInSum > 1) {
        int j = 0;

        for (; j < arr.length && newNumOfElemsInSum > 1 && remainderSum > 0; j++) {
          remainderSum -= arr [j];
          newNumOfElemsInSum--;
        }
        if (remainderSum > 0 && newNumOfElemsInSum == 1 && Arrays.binarySearch(arr, remainderSum) > j) {
          sumExists = true;
        }
      } else if (remainderSum > 0 && newNumOfElemsInSum == 1) {
        int index = Arrays.binarySearch(arr, remainderSum);
        if (index > 0 && index != i) {
          sumExists = true;
        }
      }

    }

    return sumExists;
  }

}
