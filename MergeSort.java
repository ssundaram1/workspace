
public class MergeSort {

  private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++)
      aux [k] = a [k];
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid)
        a [k] = aux [j++];
      else if (j > hi)
        a [k] = aux [i++];
      else if ((aux [j] < aux [i])) {
        a [k] = aux [j++];
      } else
        a [k] = aux [i++];
    }
  }

  // readable version
  private static void merge2(int[] a, int[] aux, int start, int mid, int end) {

    // initialize aux
    for (int k = start; k <= end; k++) {
      aux [k] = a [k];
    }

    int i = start, j = mid + 1;
    for (int k = start; k <= end; k++) {
      if (aux [i] < aux [j]) {
        a [k] = aux [i++];

      } else {
        a [k] = aux [j++];
      }
      if (i > mid) {
        a [k] = a [j++];
      } else if (j > end) {
        a [k] = a [i++];
      }

    }
  }

  private static void sortv3(int[] a, int[] aux, int start, int end) {
    if (end > start) {
      int middle = (start + end) / 2;
      sortv3(a, aux, start, middle);
      sortv3(a, aux, start, middle);
      merge(a, aux, start, middle, end);

    }

  }

  private static void sortv2(int[] a, int[] aux, int start, int end) {
    // if(start <=end) return;
    if (end > start) {
      int middle = (start + end) / 2;
      sortv2(a, aux, start, middle);
      sortv2(a, aux, middle + 1, end);
      merge(a, aux, start, middle, end);

    }
  }

  private static void sort(int[] a, int[] aux, int lo, int hi) {
    if (hi <= lo) {
      System.out.println("returning for lo: " + lo + ", high : ");
      return;
    }
    //
    int mid = lo + (hi - lo) / 2;
    System.out.println("sort lo: " + lo + ", high : " + mid);
    System.out.println("sort lo: " + lo + ", high : " + hi);
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
    System.out.println("sorted array : " + a);

  }

  public static void sort(int[] a) {
    int[] aux = new int[a.length];
    sort(a, aux, 0, a.length - 1);
  }

  private class Node {
    int data;
    Node next;
  }

  public void mergeSortLinkedList(Node head) {
    if (head == null || head.next == null) {
      return;
    }
    Node middle = findMiddle(head);
    Node sHalf = middle.next;
    middle.next = null;
    mergeSortLinkedList(head);
    mergeSortLinkedList(sHalf);
    mergev2(head, sHalf);
  }

  private Node mergev2(Node head, Node sHalf) {
    Node newHead = new Node();
    Node temp = newHead;
    Node p1 = head;
    Node p2 = sHalf;
    while (p1 != null && p2 != null) {
      if (p1.data < p2.data) {
        temp.next = p1;
        p1 = p1.next;
      } else {
        temp.next = p2;
        p2 = p2.next;
      }
      temp = temp.next;
    }

    return newHead.next;

  }

  private Node findMiddle(Node head) {
    Node slow = head;
    Node fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public Node merge_sort(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node middle = getMiddle(head); // get the middle of the list
    Node sHalf = middle.next;
    middle.next = null; // split the list into two halfs

    return merge(merge_sort(head), merge_sort(sHalf)); // recurse on that
  }

  public Node merge(Node a, Node b) {
    Node dummyHead, curr;
    dummyHead = new Node();
    curr = dummyHead;
    while (a != null && b != null) {
      if (a.data <= b.data) {
        curr.next = a;
        a = a.next;
      } else {
        curr.next = b;
        b = b.next;
      }
      curr = curr.next;
    }
    curr.next = (a == null) ? b : a;
    return dummyHead.next;
  }

  public Node getMiddle(Node head) {
    if (head == null) {
      return head;
    }
    Node slow, fast;
    slow = fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public static void main(String[] args) {
    sort(new int[] { 1, 2, 3, 4, 5 });

  }

}
