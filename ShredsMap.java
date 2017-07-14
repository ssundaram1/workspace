import java.io.Serializable;

public class ShredsMap<K extends Serializable, V> {

  private final Entry[] entryBuckets;
  private final int DEFAULT_BUCKET_COUNT = 10;

  public ShredsMap(int capacity) {
    entryBuckets = new Entry[capacity];
  }

  public ShredsMap() {
    entryBuckets = new Entry[DEFAULT_BUCKET_COUNT];
  }

  public void put(K key, V value) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    Entry<K, V> newEntry = new Entry<K, V>(key, value);
    int bucketIndex = findBucketUsingHashFunction(key.hashCode());

    if (entryBuckets [bucketIndex] == null) {
      entryBuckets [bucketIndex] = newEntry;
    } else {
      Entry<K, V> entry = entryBuckets [bucketIndex];
      boolean done = false;

      // h->1->2->3->null
      // while (entry.getNext() != null) {
      // if (key.equals(entry.getKey())) {
      // entry.setValue(value);
      // done = true;
      // break;
      // }
      //
      // entry = entry.getNext();
      // }

      // keep looking for this entry if found override as shown above else link it to the end
      Entry previous = null;
      Entry current = entry;
      while (current != null) {
        if (current.getKey().equals(newEntry.getKey())) {
          // override value
          current = newEntry;
          break;
        }

      }
      if (current == null) {
        previous.setNext(newEntry);
      }

      // if (!done) {
      // entry.setNext(new Entry<K, V>(key, value));
      // }

    }

  }

  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    int bucketIndex = findBucketUsingHashFunction(key.hashCode());

    Entry<K, V> entry = entryBuckets [bucketIndex];
    // Loop through bucket if multiple entries.
    // current entry will be found once the inputKey is equal to entryKey
    // Collisions: Two different keys have same hashCode. It results that both Entries with respective keys
    // are added into same bucket.
    while (entry != null && !key.equals(entry.getKey())) {
      entry = entry.getNext();
    }
    return entry != null ? entry.getValue() : null;

  }

  private int findBucketUsingHashFunction(int hashCode) {
    // TODO Auto-generated method stub
    return hashCode % entryBuckets.length;
  }

  private static class Entry<K, V> {
    private final K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;

    }

    private V getValue() {
      return value;
    }

    private void setValue(V value) {
      this.value = value;
    }

    private K getKey() {
      return key;
    }

    private Entry<K, V> getNext() {
      return next;
    }

    private void setNext(Entry<K, V> next) {
      this.next = next;
    }

  }

  public static void main(String[] args) {
    ShredsMap<Integer, String> testMap = new ShredsMap<Integer, String>();
    testMap.put(1, "1");
    testMap.put(1, "2");
    System.out.println(testMap.toString());

  }

}
