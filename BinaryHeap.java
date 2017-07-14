
/**
 * CSE 373, Winter 2011, Jessica Miller
 * The BinaryHeap is an -generic- implementation of the PriorityQueue interface.
 * This is a binary min-heap implementation of the priority queue ADT.
 */
import java.util.Arrays;

public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
  private static final int DEFAULT_CAPACITY = 10;
  protected T[] array;
  protected int size;

  /**
   * Constructs a new BinaryHeap.
   */
  @SuppressWarnings("unchecked")
  public BinaryHeap() {
    // Java doesn't allow construction of arrays of placeholder data types
    array = (T[]) new Comparable[DEFAULT_CAPACITY];
    size = 0;
  }

  // public void insert(Comparable x)
  // {
  // if(size == heap.length - 1) doubleSize();
  //
  // //Insert a new item to the end of the array
  // int pos = ++size;
  //
  // //Percolate up
  // for(; pos > 1 && x.compareTo(heap[pos/2]) < 0; pos = pos/2 )
  // heap[pos] = heap[pos/2];
  //
  // heap[pos] = x;
  // }

  /**
   * Adds a value to the min-heap.
   */
  public void add(T value) {
    // grow array if needed
    if (size >= array.length - 1) {
      array = this.resize();
    }

    // place element into heap at bottom

    array [++size] = value;

    bubbleUp();
  }

  /**
   * Returns true if the heap has no elements; false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns (but does not remove) the minimum element in the heap.
   */
  public T peek() {
    if (this.isEmpty()) {
      throw new IllegalStateException();
    }

    return array [1];
  }

  /**
   * Removes and returns the minimum element in the heap.
   */
  public T remove() {
    // what do want return?
    // T result = array[1];
    T result = peek();

    // get rid of the last leaf/decrement
    array [1] = array [size];
    array [size] = null;
    size--;

    bubbleDown();

    return result;
  }

  /**
   * Returns a String representation of BinaryHeap with values stored with
   * heap structure and order properties.
   */
  @Override
  public String toString() {
    return Arrays.toString(array);
  }

  /**
   * Performs the "bubble down" operation to place the element that is at the
   * root of the heap in its correct place so that the heap maintains the
   * min-heap order property.
   */
  protected void bubbleDown() {
    int index = 1;

    // bubble down
    // basically check if index *2 <= size
    // opposite of bubble up check where index >1
    while (hasLeftChild(index)) {
      // which of my children is smaller?

      int smallerChild = leftIndex(index);

      // bubble with the smaller child, if I have a smaller child
      // has right child = index *2 +1 <= size
      if (hasRightChild(index) && array [leftIndex(index)].compareTo(array [rightIndex(index)]) > 0) {
        smallerChild = rightIndex(index);
      }

      if (array [index].compareTo(array [smallerChild]) > 0) {
        swap(index, smallerChild);
      } else {
        // otherwise, get outta here!
        break;
      }

      // make sure to update loop counter/index of where last el is put
      index = smallerChild;
    }
  }

  private void trickleUpv2prac() {
    int index = size;
    int parentIndex = index / 2;
    T bottom = array [index];
    while (index > 1 && array [parentIndex].compareTo(bottom) > 0) {
      // swap
      array [index] = array [parentIndex];
      index = parentIndex;
      parentIndex = parentIndex / 2;
    }
    array [index] = bottom;
  }

  private void trickleUp() {
    int index = this.size;
    int parentIndex = (index) / 2;
    T bottom = array [index];
    while (index > 1 && array [parentIndex].compareTo(bottom) > 0) {
      // move larger parent downwards.
      array [index] = array [parentIndex];
      index = parentIndex;
      parentIndex = (parentIndex) / 2;
    }
    array [index] = bottom;
  }

  /**
   * Performs the "bubble up" operation to place a newly inserted element
   * (i.e. the element that is at the size index) in its correct place so
   * that the heap maintains the min-heap order property.
   */
  protected void bubbleUp() {
    int index = this.size;

    while (hasParent(index) && (parent(index).compareTo(array [index]) > 0)) {
      // parent/child are out of order; swap them
      swap(index, parentIndex(index));
      index = parentIndex(index);
    }
  }

  protected boolean hasParent(int i) {
    return i > 1;
  }

  protected int leftIndex(int i) {
    return i * 2;
  }

  protected int rightIndex(int i) {
    return i * 2 + 1;
  }

  protected boolean hasLeftChild(int i) {
    return leftIndex(i) <= size;
  }

  protected boolean hasRightChild(int i) {
    return rightIndex(i) <= size;
  }

  protected T parent(int i) {
    return array [parentIndex(i)];
  }

  protected int parentIndex(int i) {
    return i / 2;
  }

  protected T[] resize() {
    return Arrays.copyOf(array, array.length * 2);
  }

  protected void swap(int index1, int index2) {
    T tmp = array [index1];
    array [index1] = array [index2];
    array [index2] = tmp;
  }
}