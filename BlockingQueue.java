import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// stackoverflow.com/questions/2536692/a-simple-scenario-using-wait-and-notify-in-java
public class BlockingQueue<T> {

  private final Queue<T> queue = new LinkedList<T>();
  private final int capacity;

  public BlockingQueue(int capacity) {
    this.capacity = capacity;
  }

  public synchronized void put(T element) throws InterruptedException {
    while (queue.size() == capacity) {
      wait();
    }

    queue.add(element);
    notify(); // notifyAll() for multiple producer/consumer threads
  }

  public synchronized T take() throws InterruptedException {
    while (queue.isEmpty()) {
      wait();
    }

    T item = queue.remove();
    notify(); // notifyAll() for multiple producer/consumer threads
    return item;
  }
}

// second implementation

public class BlockingQueue<T> {

  private Queue<T> queue = new LinkedList<T>();
  private int capacity;
  private Lock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  public BlockingQueue(int capacity) {
    this.capacity = capacity;
  }

  public void put(T element) throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == capacity) {
        notFull.await();
      }

      queue.add(element);
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public T take() throws InterruptedException {
    lock.lock();
    try {
      while (queue.isEmpty()) {
        notEmpty.await();
      }

      T item = queue.remove();
      notFull.signal();
      return item;
    } finally {
      lock.unlock();
    }
  }
}