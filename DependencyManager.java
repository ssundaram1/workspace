
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author shreedhar
 *
 */
public class DependencyManager {

  private final Map<String, LinkedHashSet<String>> components = new HashMap<String, LinkedHashSet<String>>();

  List<String> topologicalOrderedComps = new ArrayList<String>();
  Set<String> installedComps = new LinkedHashSet<String>();
  Set<String> explicitlyInstalledComponents = new LinkedHashSet<String>();

  // c1 depends on c2
  // c2 should be installed before c1

  // note - c2 could be a list going forward for simplicity right now only single element
  public void addEdge(String c2, String c1) {
    System.out.println(c1 + " DEPEND ON " + c2);

    if (!components.containsKey(c1)) {
      components.put(c1, new LinkedHashSet<String>());
    }
    if (!components.containsKey(c2)) {
      components.put(c2, new LinkedHashSet<String>());
    }

    /* Add the edge. */
    components.get(c2).add(c1);

  }

  public void install(String c) {

    System.out.println("INSTALL " + c);

    if (installedComps.contains(c)) {
      System.out.println(c + " already installed!");
      return;
    }

    // use topolocially ordered list and find all dependencies
    int i = 0;
    List<String> dependencies = new ArrayList<String>();
    // itreate until c is found
    while (i < topologicalOrderedComps.size() && !c.equals(topologicalOrderedComps.get(i))) {

      // check if the current component is needed to install c
      // would mean an edge from current component to c
      if (components.get(topologicalOrderedComps.get(i)).contains(c)) {
        dependencies.add(topologicalOrderedComps.get(i));

      }
      i++;
    }

    for (String d : dependencies) {
      if (!installedComps.contains(d)) {
        System.out.println("Installing dependcies  : " + d);
        installedComps.add(d);

      }
    }

    System.out.println("Installing comp  : " + c);
    installedComps.add(c);
    explicitlyInstalledComponents.add(c);

  }

  public void remove(String c) {

    System.out.println("REMOVE " + c);

    // use ordered list and find all dependencies
    int i = 0;
    if (!installedComps.contains(c)) {
      System.out.println(c + " not installed !");
      return;
    }
    if (isCompBeingUsedByInstalledComps(components.get(c), c)) {
      System.out.println("removing comp : " + c);

      installedComps.remove(c);

      // itreate over topological ordered list until c is found

      while (i < topologicalOrderedComps.size() && !c.equals(topologicalOrderedComps.get(i))) {

        // for each component - check if component USED BY c, if none of the other components use it
        // and it wasn't installed explicitly then remove it

        Set<String> usedBy = components.get(topologicalOrderedComps.get(i));
        if (usedBy.contains(c) && !explicitlyInstalledComponents.contains(topologicalOrderedComps.get(i))) {
          // recursive remove to remove dependencies that were not explicitly installed
          remove(topologicalOrderedComps.get(i));

        }
        i++;
      }

    }

  }

  public String getUsedBy(String c) {
    return components.get(c).toString();

  }

  // check not being used by any installed comps
  private boolean isCompBeingUsedByInstalledComps(Set<String> usedBy, String dependency) {
    for (String ic : installedComps) {
      if (usedBy.contains(ic)) {
        System.out.println(dependency + " is STILL NEEDED ");
        return false;
      }
    }
    return true;
  }

  private void listInstalledComponents() {
    System.out.println("installed comps are :" + installedComps.toString());
  }

  public List<String> topSort() {

    HashSet<String> visited = new HashSet<String>();

    Stack<String> stack = new Stack<String>();
    // iterate over all ndoes
    for (String component : components.keySet()) {
      if (!visited.contains(component)) {
        topSortUtil(visited, stack, component);
      }

    }

    while (!stack.isEmpty()) {
      topologicalOrderedComps.add(stack.pop());

    }
    return topologicalOrderedComps;
  }

  private void topSortUtil(HashSet<String> visited, Stack<String> stack, String component) {

    // System.out.println("At comp: " + component);

    visited.add(component);
    for (String c : components.get(component)) {
      if (!visited.contains(c)) {
        visited.add(c);
        topSortUtil(visited, stack, c);

      }

    }
    // System.out.println("pushing component: " + component + " onto stack");
    stack.push(component);

  }

  public static void main(String args[]) {

    // read from file, doing this in memory to get functionality right
    DependencyManager g = new DependencyManager();
    List<String> commands = new ArrayList<String>();

    // Creating a USED-BY graph of commands
    // arg1 is used by arg2 so has an edge from arg1 outgoing to arg2
    // it is easier to construct graph in this direction since it is easier to understand how if comp c is being used by how man comps
    g.addEdge("TCPIP", "TELNET");
    g.addEdge("NETCARD", "TCPIP");
    g.addEdge("TCPIP", "DNS");
    g.addEdge("NETCARD", "DNS");
    g.addEdge("TCPIP", "BROWSER");
    g.addEdge("HTML", "BROWSER");

    // get a sorted order of the graph once we are done adding these dependencies
    g.topSort();
    g.install("NETCARD");
    g.install("TELNET");
    g.install("foo");
    g.remove("NETCARD");
    g.install("BROWSER");
    g.install("DNS");
    // System.out.println("installed comps are :" + g.installedComps.toString());
    g.listInstalledComponents();

    g.remove("TELNET");
    g.remove("NETCARD");
    g.remove("DNS");
    g.remove("NETCARD");
    g.install("NETCARD");
    g.remove("TCPIP");
    g.remove("BROWSER");
    g.remove("TCPIP");
    // System.out.println("installed comps are :" + g.installedComps.toString());
    g.listInstalledComponents();

  }

}