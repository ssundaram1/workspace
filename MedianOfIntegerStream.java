import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

// Given a stream of unsorted integers, find the median element in sorted order at any given time.
// http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/
public class MedianOfIntegerStream {

  public Queue<Integer> minHeap;
  public Queue<Integer> maxHeap;
  public int numOfElements;

  public MedianOfIntegerStream() {
    minHeap = new PriorityQueue<Integer>();
    maxHeap = new PriorityQueue<Integer>(10, new MaxHeapComparator());
    numOfElements = 0;
  }

  // 1234567
  // 2 parts to the problem

  // keeping 2 heaps

  // add elem to max Q
  // algo: if odd num elems in Q, simple pop the top of max Q add it to the min Q
  // if even, check swap the top of minheap with maxheap if required

  public void addNumberToStream(Integer num) {

    // both cases u add elements to max heap
    maxHeap.add(num);
    if (numOfElements % 2 == 0) {
      if (minHeap.isEmpty()) {
        numOfElements++;
        return;
      } else if (maxHeap.peek() > minHeap.peek()) {
        Integer maxHeapRoot = maxHeap.poll();
        Integer minHeapRoot = minHeap.poll();
        maxHeap.add(minHeapRoot);
        minHeap.add(maxHeapRoot);
      }
    } else {
      // if odd numelements u poll from max heap
      minHeap.add(maxHeap.poll());
    }
    numOfElements++;
  }

  public void addNumberToStreamv2(Integer num) {

    // in case of odd number pop from max heap into min heap n+1 and n elems
    // in case of even if new number > min heap peek pop min heap and insert

    if (numOfElements % 2 == 0 && !minHeap.isEmpty() && num > minHeap.peek()) {
      maxHeap.add(minHeap.poll());
      minHeap.add(num);
    } else

    {
      maxHeap.add(num);
      minHeap.add(maxHeap.poll());
    }
    numOfElements++;

  }

  public Double getMedian() {
    System.out.println("median for stream is : " + maxHeap.toString() + minHeap.toString());
    if (numOfElements % 2 != 0)
      return new Double(maxHeap.peek());
    else
      return (maxHeap.peek() + minHeap.peek()) / 2.0;

  }

  private class MaxHeapComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
      return o2 - o1;
    }
  }

  public static int randInt(int min, int max) {

    Random rand = new Random();

    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }

  public static void main(String[] args) {
    MedianOfIntegerStream streamMedian = new MedianOfIntegerStream();

    // streamMedian.addNumberToStream(1);
    // System.out.println(streamMedian.getMedian()); // should be 1
    //
    // streamMedian.addNumberToStream(5);
    // streamMedian.addNumberToStream(10);
    // streamMedian.addNumberToStream(12);
    // streamMedian.addNumberToStream(2);
    // System.out.println(streamMedian.getMedian()); // should be 5
    //
    // streamMedian.addNumberToStream(3);
    // streamMedian.addNumberToStream(8);
    // streamMedian.addNumberToStream(9);
    // System.out.println(streamMedian.getMedian()); // should be 6.5

    streamMedian.addNumberToStream(randInt(5, 10));
    streamMedian.addNumberToStream(randInt(5, 10));
    streamMedian.addNumberToStream(randInt(5, 10));
    streamMedian.addNumberToStream(randInt(5, 10));
    System.out.println(streamMedian.getMedian());
    streamMedian.addNumberToStream(randInt(5, 10));
    streamMedian.addNumberToStream(randInt(5, 10));
    streamMedian.addNumberToStream(randInt(5, 10));
    System.out.println(streamMedian.getMedian());
  }
}