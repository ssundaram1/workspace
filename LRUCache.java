import java.util.HashMap;

public class LRUCache<K, V> {

  // least_recently_used -> A <-> B <-> C <-> D <-> E <- most_recently_used

  // Define Node with pointers to the previous and next items and a key, value pair
  class Node<T, U> {
    Node<T, U> previous;
    Node<T, U> next;
    T key;
    U value;

    public Node(Node<T, U> previous, Node<T, U> next, T key, U value) {
      this.previous = previous;
      this.next = next;
      this.key = key;
      this.value = value;
    }
  }

  private final HashMap<K, Node<K, V>> cache;
  private Node<K, V> leastRecentlyUsed;
  private Node<K, V> mostRecentlyUsed;
  private final int maxSize;
  private int currentSize;

  public LRUCache(int maxSize) {
    this.maxSize = maxSize;
    this.currentSize = 0;
    leastRecentlyUsed = new Node<K, V>(null, null, null, null);
    mostRecentlyUsed = leastRecentlyUsed;
    cache = new HashMap<K, Node<K, V>>();
  }

  /**
   * 
   * @param key
   * @return
   *         algo --> look up by key, return the node value if mru
   *         else --> if MRU return else move node to mru and adjust prev and next pointers
   * 
   */

  public V get(K key) {
    Node<K, V> tempNode = cache.get(key);
    if (tempNode == null) {
      return null;
    }
    // If MRU leave the list as it is
    else if (tempNode.key == mostRecentlyUsed.key) {
      return mostRecentlyUsed.value;
    }

    // Get the next and previous nodes
    Node<K, V> nextNode = tempNode.next;
    Node<K, V> previousNode = tempNode.previous;

    // If at the left-most, we update LRU
    if (tempNode.key == leastRecentlyUsed.key) {
      nextNode.previous = null;
      leastRecentlyUsed = nextNode;
    }

    // If we are in the middle, we need to update the items before and after our item
    else if (tempNode.key != mostRecentlyUsed.key) {
      previousNode.next = nextNode;
      nextNode.previous = previousNode;
    }

    // Finally move our item to the MRU
    tempNode.previous = mostRecentlyUsed;
    mostRecentlyUsed.next = tempNode;
    mostRecentlyUsed = tempNode;
    mostRecentlyUsed.next = null;

    return tempNode.value;

  }

  public void display() {
    if (currentSize > 0) {
      Node n = leastRecentlyUsed;
      while (n != null) {
        System.out.println(n.value);
        n = n.next;
      }
    }
  }

  /**
   * 
   * @param key
   * @param value
   * 
   *          algo --> set the new node as MRU
   *          if maxSize reached get rid of LRU from cache and list both
   *          else increment size
   *          if size ==0 initialize LRU
   */
  public void put(K key, V value) {
    if (cache.containsKey(key)) {
      return;
    }

    // Put the new node at the right-most end of the linked-list
    Node<K, V> myNode = new Node<K, V>(mostRecentlyUsed, null, key, value);
    mostRecentlyUsed.next = myNode;
    cache.put(key, myNode);
    mostRecentlyUsed = myNode;

    // Delete the left-most entry and update the LRU pointer
    if (currentSize == maxSize) {
      cache.remove(leastRecentlyUsed.key);
      leastRecentlyUsed = leastRecentlyUsed.next;
      leastRecentlyUsed.previous = null;
    }

    // Update cache size, for the first added entry update the LRU pointer
    else if (currentSize < maxSize) {
      if (currentSize == 0) {
        leastRecentlyUsed = myNode;
      }
      currentSize++;
    }
  }

  public static void main(String[] args) {
    LRUCache<String, Integer> cache = new LRUCache<String, Integer>(5);
    cache.put("abc", 1);
    // cache.display();
    cache.put("def", 2);
    cache.put("ghi", 3);
    cache.put("xyz", 4);
    cache.put("xab", 5);
    cache.put("xbc", 6);
    cache.get("xyz");
    cache.display();

  }
}