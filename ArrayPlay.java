import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayPlay {

  /*
   * BFS
   * DFS
   * Topological Sort & Shortest-path in a DAG
   * Dijkstra's algorithm
   * Bellman-Ford
   * A-star (A*)
   * 
   */

  // schedule A = {{1,15}, {25,50}}
  // schedule B = {{10,90}}
  private static void getAvaial(int[][] scheduleA, int[][] scheduleB, int duration) {

    int i = 0, j = 0;
    int[] a = scheduleA [0];
    int b[] = scheduleB [0];
    System.out.println("length of a is " + scheduleA.length + " length of b is " + scheduleB.length);
    while (i < scheduleA.length && j < scheduleB.length) {
      a = scheduleA [i];
      b = scheduleB [j];
      int start = (a [0] > b [0]) ? a [0] : b [0];
      int end = (a [1] < b [1]) ? a [1] : b [1];
      if (end - start >= duration) {
        System.out.println(start);
        break;
      } else if (a [1] < b [1]) {
        i++;
      } else {
        j++;
      }

    }

  }

  // evens to left
  private static int[] oddSort(int[] items) {
    int oddPos, nextEvenPos;
    for (nextEvenPos = 0; nextEvenPos < items.length && items [nextEvenPos] % 2 == 0; nextEvenPos++) {
    }
    // nextEvenPos is now positioned at the first odd number in the array,
    // i.e. it is the next place an even number will be placed

    // We already know that items[nextEvenPos] is odd (from the condition of the
    // first loop), so we'll start looking for even numbers at nextEvenPos + 1
    for (oddPos = nextEvenPos + 1; oddPos < items.length; oddPos++) {
      // If we find an even number
      if (items [oddPos] % 2 == 0) {
        // Swap the values
        int temp = items [nextEvenPos];
        items [nextEvenPos] = items [oddPos];
        items [oddPos] = temp;
        // And increment the location for the next even number
        nextEvenPos++;
      }
    }

    return items;
  }

  public int findPeakElement(int[] num) {
    int max = num [0];
    int index = 0;
    for (int i = 1; i <= num.length - 2; i++) {
      int prev = num [i - 1];
      int curr = num [i];
      int next = num [i + 1];
      if (curr > prev && curr > next && curr > max) {
        index = i;
        max = curr;
      }
    }
    if (num [num.length - 1] > max) {
      return num.length - 1;
    }
    return index;
  }

  // 012345
  // 450123

  // 01234
  // 23401
  // 34012
  // 40123
  /**
   * rotated sorted array find min
   * 
   * @param num
   * @return
   */
  public int findMin(int[] num) {
    return findMin(num, 0, num.length - 1);
  }

  public int findMin(int[] num, int left, int right) {
    if (left == right)
      return num [left];
    if ((right - left) == 1)
      return Math.min(num [left], num [right]);
    int middle = left + (right - left) / 2;
    // not rotated
    if (num [left] < num [right]) {
      return num [left];
      // go right side
    } else if (num [middle] > num [left]) {
      return findMin(num, middle, right);
      // go left side
    } else {
      return findMin(num, left, middle);
    }
  }

  private static int[] evensToLeft(int[] arr) {
    int p1 = 0;

    if (arr.length < 2) {
      return arr;
    }

    // stop at the first odd
    for (int i = 0; i < arr.length; i++) {
      if (arr [i] % 2 != 0) {
        p1 = i;
        break;
      }
    }

    // start from the next pos of p1 and when found an even swap
    for (int j = p1 + 1; j < arr.length; j++) {
      if (arr [j] % 2 == 0) {
        // swap
        // we found the next even num at j
        int temp = arr [p1];
        arr [p1] = arr [j];
        arr [j] = temp;
        p1++;
      }

    }
    return arr;

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
    for (int i = arr.length - 1; i > Z - 1 && !sumExists; i++) {
      int a = arr [i];
      // initial arr {1,1,1,2,3,4,5,7,8,10}
      // initial Z = 4, N=11
      // at element 5 in the arr -> a = 6, Z= 4 , newArrSum = 5
      int newArrSum = N - a;
      int newNumOfElemsInSum = Z - 1;

      int remainderSum = 0;
      // do this until z until 1
      int j = 0;
      for (; j < arr.length && newNumOfElemsInSum == 1; j++) {
        remainderSum -= arr [j];
        newNumOfElemsInSum--;
      }
      // this means index was found and after j
      if (Arrays.binarySearch(arr, remainderSum) > j) {
        sumExists = true;
      }

    }
    return sumExists;
  }

  public static void findPairEqualsSum(int[] arr, int sum) {
    if (arr.length < 2) {
      return;
    }
    if (arr.length == 2) {
      if (arr [0] + arr [1] == sum) {
        System.out.println(Arrays.toString(arr));
      }
    }
    Set<Integer> sumPair = new HashSet<Integer>();
    for (int i = 0; i < arr.length; i++) {
      if (!sumPair.contains(arr [i])) {
        sumPair.add((sum - arr [i]));
      } else {
        System.out.println("Pair equals sum is :" + arr [i] + " and " + (sum - arr [i]));
      }
    }
  }

  public static int removeDuplicatesv2(int[] arr) {
    int length = arr.length;
    if (length == 0 || length == 1)
      return length;
    int i = 1;
    for (int j = 1; j < length; j++) {
      if (arr [j] != arr [j - 1]) {
        arr [i] = arr [j];
        i++;
      }
    }
    if (i < length)
      arr [i] = '\0';
    return ++i;
  }

  // {1,1,2,2,3,3}
  public static int removeDuplicates(int[] arr) {
    int length = arr.length;
    if (length == 0 || length == 1)
      return length;
    // sort array
    Arrays.sort(arr);
    // define 2 pointers
    int nextUniquePos = 1;
    for (int j = 0; j < length - 1; j++) {
      //
      if (arr [j] != arr [j + 1]) {
        arr [nextUniquePos] = arr [j + 1];
        nextUniquePos++;
      }
    }

    if (nextUniquePos <= length - 1) {

    }

    return ++nextUniquePos;
  }

  // 345
  // 50

  // public int[] add(int[] a, int[] b) {
  //
  // int i = a.length;
  // int j = b.length;
  // int[] c = new int[(Math.max(i, j)) + 1];
  // int k = 0;
  // int balance = 0;
  // while (i >= 0 && j >= 0) {
  //
  // int sum = a [i--] + b [j--] + balance;
  // if (sum > 9) {
  // c [k++] = sum % 10;
  // balance = sum / 10;
  //
  // } else {
  // c [k++] = sum;
  // }
  //
  // }
  //
  // }

  //
  // 01234
  // k = 1 -->40123
  // k =3 --> 23401
  public static void rotate(int[] nums, int k) {
    if (k > nums.length)
      k = k % nums.length;

    int[] result = new int[nums.length];

    for (int i = 0; i < k; i++) {
      result [i] = nums [nums.length - k + i];
    }

    int j = 0;
    for (int i = k; i < nums.length; i++) {
      result [i] = nums [j];
      j++;
    }

    System.arraycopy(result, 0, nums, 0, nums.length);
  }

  // 1,1,2,3,5,8,13...
  public static void fibo(int n) {

    int prevSum1 = 1, prevSum2 = 0;
    int newSum = 1;
    int temp = 0;

    // System.out.println(prevSum1 + prevSum2);
    for (int i = 1; i < n; i++) {

      System.out.println(prevSum1 + prevSum2);
      temp = prevSum2;
      prevSum2 = prevSum1;
      prevSum1 = prevSum1 + temp;

    }

  }

  // Count the number of unique elements
  public static int countUnique(int[] A) {
    int count = 0;
    for (int i = 0; i < A.length - 1; i++) {
      if (A [i] == A [i + 1]) {
        count++;
      }
    }
    return(A.length - count);
  }

  // maximumSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4]);
  // Prints:
  //
  // 0 0
  // 0 0 -2
  // 1 1 1
  // 0 1 -3
  // 4 4 4
  // 3 4 -1
  // 5 5 2
  // 6 6 1
  // 1 6 -5
  // 5 6 4
  // 5 6

  public static String findMaxSubArrayUsingFor(int[] inputArray) {
    int maxSoFar = 0;
    int maxEndingHere = 0;
    int maxStartIndex = 0;
    int maxEndIndex = 0;
    for (int i = 0; i < inputArray.length; i++) {
      System.out.println("ending here " + maxEndingHere);
      System.out.println("so far " + maxSoFar);
      if (maxEndingHere + inputArray [i] > 0) {
        maxEndingHere = maxEndingHere + inputArray [i];
      } else {
        maxEndingHere = 0;
        maxStartIndex = i + 1;
      }
      if (maxEndingHere > maxSoFar) {
        maxSoFar = maxEndingHere;
        maxEndIndex = i;
      }
    }
    int[] maxArray = new int[0];
    if (maxStartIndex <= maxEndIndex) {
      maxArray = Arrays.copyOfRange(inputArray, maxStartIndex, maxEndIndex + 1);
    }
    return String.valueOf("\nInput-Array:" + Arrays.toString(inputArray) + "\nMax: " + maxSoFar + "\nSub-Array:" + Arrays.toString(maxArray));
  }

  public void sortByGroupSize(Integer[] arr, int grpSize) {

    // 1,2,3,4,5,6,7,8 ==> 3,2,1,6,5,4,8,7 for size = 3

    Arrays.sort(arr);
    List<Integer> list = Arrays.asList(arr);
    List<Integer> subList = new ArrayList<Integer>();
    List<Integer> finalList = new ArrayList<Integer>();
    // 8/3 = 2.6 which becomes 3
    double temp = Math.ceil(arr.length / (double) grpSize);

    for (int i = 0; i < temp; i++) {
      if ((i + 1) * grpSize <= arr.length - 1)
        subList = list.subList(i * grpSize, (i + 1) * grpSize);
      else
        subList = list.subList(i * grpSize, arr.length);

      Collections.reverse(subList);
      finalList.addAll(subList);
    }

    System.out.println(finalList);

  }

  public static void main(String[] args) {
    int[] arr = { 2, 3, 3 };
    // int length = removeDuplicatesv2(arr);
    // System.out.println(length);
    rotate(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3);
    fibo(6);
    findPairEqualsSum(new int[] { 0, 1, 2, 3, 4 }, 5);

    System.out.println(removeDuplicates(new int[] { 2, 3, 3, 4, 4, 4, 5 }));

    System.out.println(removeDuplicatesv2(new int[] { 2, 3, 3, 4, 4, 4, 5 }));

    System.out.println(" Earliest avail time: ");
    getAvaial(new int[][] { { 1, 15 }, { 25, 50 } }, new int[][] { { 10, 90 } }, 20);

  }

}
