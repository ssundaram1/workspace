import java.lang.reflect.Array;
import java.util.Arrays;

public class Queue<E> {

  E[] arr;
  int last = -1;
  int first = -1;
  int size;

  public Queue(Class<E> c, int size) {
    E[] newInstance = (E[]) Array.newInstance(c, size);

    this.arr = newInstance;
    this.size = 0;
  }

  boolean push(E e) {
    if (size == arr.length)
      return false;

    last = (last + 1) % arr.length;
    arr [last] = e;
    size++;

    if (first == -1) {
      first = last;
    }

    return true;
  }

  public E popv1() {
    if (size == 0) {
      return null;
    }
    E elem = arr [first];
    arr [first] = null;
    size--;
    first = (first + 1) % arr.length;
    return elem;
  }

  boolean pop() {
    if (size == 0) {
      return false;
    }

    E result = arr [first];
    arr [first] = null;
    size--;
    first = (first + 1) % arr.length;

    if (size == 0) {
      last = -1;
      first = -1;
    }

    return true;
  }

  E peek() {
    if (size == 0)
      return null;

    return arr [first];
  }

  public int size() {
    return this.size;
  }

  @Override
  public String toString() {
    return Arrays.toString(this.arr);
  }

  public static void main(String[] args) {
    Queue<Integer> q = new Queue<Integer>(Integer.class, 5);
    q.push(1);
    q.push(2);
    q.push(3);
    q.push(4);
    q.push(5);
    q.pop();
    q.push(6);
    System.out.println(q);
  }

}