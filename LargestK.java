import java.util.PriorityQueue;

public class LargestK {

  private static Integer largestK(Integer array[], int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k + 1);
    int i = 0;
    while (i <= k) {
      queue.add(array [i]);
      i++;
    }
    for (; i < array.length; i++) {
      Integer value = queue.peek();
      if (array [i] > value) {
        queue.poll();
        queue.add(array [i]);
      }
    }
    return queue.peek();
  }

  private static int kthSmallest(int[] array, int i) {
    return kthSmallest(array, 0, array.length - 1, i);

  }

  public static int kthSmallest(int arr[], int l, int r, int k) {

    if (k > 0 && k <= r - l + 1) {

      int pos = partitionv2(arr, l, r);

      if (pos == k)
        return arr [pos];
      if (pos > k) // If position is more, recur for left subarray
        return kthSmallest(arr, l, pos - 1, k);

      return kthSmallest(arr, pos + 1, r, k);
    }

    return Integer.MAX_VALUE;
  }

  // If k is more than number of elements in array

  // This method is used to swap the values between the two given index
  public static void swap(int[] a, int left, int right) {
    int temp = a [left];
    a [left] = a [right];
    a [right] = temp;
  }

  public static int partitionv2(int[] a, int l, int r) {
    int pivot = a [r];
    int pIndex = 0;
    for (int i = 0; i < a.length - 1; i++) {
      if (a [i] < pivot) {
        swap(a, i, pIndex);
        pIndex++;
      }

    }
    swap(a, pIndex, r);
    return pIndex;

  }

  public static void main(String[] args) {
    int array[] = new int[] { 3, 6, 2, 8, 9, 4, 5 };
    System.out.println(largestK(array, 3));
    System.out.println(kthSmallest(array, 3));
  }

}