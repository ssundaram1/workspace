
// A Java program to print topological sorting of a DAG
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

// This class represents a directed graph using adjacency
// list representation
class Graph {
  private final int V; // No. of vertices
  private final LinkedList<Integer> adj[]; // Adjacency List

  // [4, 5, 0, 2, 3, 1]

  // Constructor
  Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; ++i)
      adj [i] = new LinkedList();
  }

  // Function to add an edge into the graph
  void addEdge(int v, int w) {
    adj [v].add(w);
  }

  // A recursive function used by topologicalSort
  void topologicalSortUtil(int v, Boolean visited[], Stack stack) {
    // Mark the current node as visited.
    visited [v] = true;
    Integer i;
    System.out.println("At vertex: " + v);

    // Recur for all the vertices adjacent to this vertex
    Iterator<Integer> it = adj [v].iterator();
    while (it.hasNext()) {
      i = it.next();
      if (!visited [i])
        topologicalSortUtil(i, visited, stack);
    }
    // System.out.println("pushing into stack: " + v);

    // Push current vertex to stack which stores result
    stack.push(new Integer(v));
  }

  // The function to do Topological Sort. It uses recursive
  // topologicalSortUtil()
  void topologicalSort() {
    Stack stack = new Stack();

    // Mark all the vertices as not visited
    Boolean visited[] = new Boolean[V];
    for (int i = 0; i < V; i++)
      visited [i] = false;

    // Call the recursive helper function to store Topological
    // Sort starting from all vertices one by one
    for (int i = 0; i < V; i++)
      if (visited [i] == false)
        topologicalSortUtil(i, visited, stack);

    // Print contents of stack
    while (stack.empty() == false)
      System.out.print(stack.pop() + " ");
  }

  // prints BFS traversal from a given source s
  void BFS(int s) {
    // Mark all the vertices as not visited(By default
    // set as false)
    boolean visited[] = new boolean[V];

    // Create a queue for BFS
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // Mark the current node as visited and enqueue it
    visited [s] = true;
    queue.add(s);

    while (queue.size() != 0) {
      // Dequeue a vertex from queue and print it
      s = queue.poll();
      System.out.print(s + " ");

      // Get all adjacent vertices of the dequeued vertex s
      // If a adjacent has not been visited, then mark it
      // visited and enqueue it
      Iterator<Integer> i = adj [s].listIterator();
      while (i.hasNext()) {
        int n = i.next();
        if (!visited [n]) {
          visited [n] = true;
          queue.add(n);
        }
      }
    }
  }

  // A function used by DFS
  void DFSUtil(int v, boolean visited[]) {
    // Mark the current node as visited and print it
    visited [v] = true;
    System.out.print(v + " ");

    // Recur for all the vertices adjacent to this vertex
    Iterator<Integer> i = adj [v].listIterator();
    while (i.hasNext()) {
      int n = i.next();
      if (!visited [n])
        DFSUtil(n, visited);
    }
  }

  // The function to do DFS traversal. It uses recursive DFSUtil()
  void DFS(int v) {
    // Mark all the vertices as not visited(set as
    // false by default in java)
    boolean visited[] = new boolean[V];

    // Call the recursive helper function to print DFS traversal
    DFSUtil(v, visited);
  }

  // Driver method
  public static void main(String args[]) {
    // Create a graph given in the above diagram
    Graph g = new Graph(6);
    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);

    System.out.println("Following is a Topological " + "sort of the given graph");
    g.topologicalSort();
  }
}