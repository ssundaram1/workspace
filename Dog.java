import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Dog implements Comparable<Dog> {

  private interface Human {

    public void goPee();
  }

  public class Male implements Human {

    @Override
    public void goPee() {
      System.out.println("Stand Up");
    }
  }

  public class Female implements Human {

    @Override
    public void goPee() {
      System.out.println("Sit Down");
    }
  }

  public void goPee(List<Human> humans) {
    for (Human h : humans) {
      h.goPee();
    }

  }

  public String color;
  public int size;
  List<Long> num = new ArrayList<Long>();

  public Dog(String color, int size) {
    this.color = color;
    this.size = size;

  }

  @Override
  public boolean equals(Object o) {
    return ((Dog) o).color.equals(this.color);
  }

  @Override
  public int hashCode() {
    return color.length();
  }

  private static void printElems(List<? extends Number> numList) {

    for (Number num : numList) {
      System.out.println(num);
    }
  }

  @Override
  public String toString() {
    return color + " dog";
  }

  public static void main(String[] args) {

    Dog d1 = new Dog("red", 20);
    Dog d2 = new Dog("black", 30);
    Dog d3 = new Dog("white", 40);
    Dog d4 = new Dog("white", 50);

    // this is gerics at play
    // covariance - print
    List<Number> num = new ArrayList<>();
    num.add(new Integer(1));
    printElems(num);
    printElems(new ArrayList<Integer>(new Integer(1)));
    printElems(new ArrayList<Double>(Arrays.asList(1.1, 2.2)));
    printElems(new ArrayList<Long>());

    TreeMap<Dog, Integer> treeMap = new TreeMap<Dog, Integer>();
    treeMap.put(d1, 10);
    treeMap.put(d2, 15);
    treeMap.put(d3, 5);
    treeMap.put(d4, 20);

    for (Entry<Dog, Integer> entry : treeMap.entrySet()) {
      System.out.println(entry.getKey() + " - " + entry.getValue());
    }

    ArrayList<Human> group = new ArrayList<Human>();

    group.add(d1.new Male());
    group.add(d1.new Female());
    // ... add more...

    // tell the class to take a pee break
    // for (Human person : group)
    // person.goPee();
    d1.goPee(group);
  }

  @Override
  public int compareTo(Dog o) {
    return this.size - o.size;

  }
}
