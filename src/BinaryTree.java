import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// Node.java
public class BinaryTree {
  // Root node pointer. Will be null for an empty tree.
  private Node root;

  private static int nValCount;

  // m
  // / \
  // a m
  // / \ / \
  // . d . .
  // / \
  // . a
  // / \
  // . .

  // This gives us the pre-order to be: m a . d . a . . m . .
  //
  // Consider the in-order, without the null nodes, it is: a d a m m

  /*
   * --Node--
   * The binary tree is built using this nested node class.
   * Each node stores one data element, and has left and right
   * sub-tree pointer which may be null.
   * The node is a "dumb" nested class -- we just use it for
   * storage; it does not have any methods.
   */

  // http://cslibrary.stanford.edu/110/BinaryTrees.html
  private static class Node {
    Node left;
    Node right;
    int data;

    Node(int newData) {
      left = null;
      right = null;
      data = newData;
    }
  }

  /**
   * Creates an empty binary tree -- a null root pointer.
   */
  public void BinaryTree() {
    root = null;
  }

  public String serialize(Node root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
  }

  private void serialize(Node x, StringBuilder sb) {
    if (x == null) {
      sb.append("# ");
    } else {
      sb.append(x.data + " ");
      serialize(x.left, sb);
      serialize(x.right, sb);
    }
  }

  public Node deserialize(String s) {
    if (s == null || s.length() == 0)
      return null;
    StringTokenizer st = new StringTokenizer(s, " ");
    return deserialize(st);
  }

  private Node deserialize(StringTokenizer st) {
    if (!st.hasMoreTokens())
      return null;
    String val = st.nextToken();
    if (val.equals("#"))
      return null;
    Node root = new Node(Integer.parseInt(val));
    root.left = deserialize(st);
    root.right = deserialize(st);
    return root;
  }

  /**
   * Returns true if the given target is in the binary tree.
   * Uses a recursive helper.
   */
  public boolean lookup(int data) {
    return(lookup(root, data));
  }

  /**
   * Recursive lookup -- given a node, recur
   * down searching for the given data.
   */
  private boolean lookup(Node node, int data) {
    if (node == null) {
      return(false);
    }

    if (data == node.data) {
      return(true);
    } else if (data < node.data) {
      return(lookup(node.left, data));
    } else {
      return(lookup(node.right, data));
    }
  }

  /**
   * Inserts the given data into the binary tree.
   * Uses a recursive helper.
   */
  public void insert(int data) {
    root = insert(root, data);
  }

  /**
   * Recursive insert -- given a node pointer, recur down and
   * insert the given data into the tree. Returns the new
   * node pointer (the standard way to communicate
   * a changed pointer back to the caller).
   */
  private Node insert(Node node, int data) {
    if (node == null) {
      node = new Node(data);
    } else {
      if (data <= node.data) {
        node.left = insert(node.left, data);
      } else {
        node.right = insert(node.right, data);
      }
    }

    return(node); // in any case, return the new pointer to the caller
  }

  /**
   * Returns the number of nodes in the tree.
   * Uses a recursive helper that recurs
   * down the tree and counts the nodes.
   */
  public int size() {
    return(size(root));
  }

  private int size(Node node) {
    if (node == null)
      return(0);
    else {
      // System.out.println("at node " + node.data);
      return(size(node.left) + 1 + size(node.right));
    }
  }

  public int heightv2(Node node) {
    if (node == null) {
      return 0;
    } else {
      int leftHeight = height(node.left);
      int rightHeight = height(node.right);
      return(Math.max(leftHeight, rightHeight) + 1);
    }
  }

  public int maxDepth() {
    return(maxDepth(root));
  }

  private int maxDepth(Node node) {
    if (node == null) {
      return(0);
    } else {
      int lDepth = maxDepth(node.left);
      int rDepth = maxDepth(node.right);

      // use the larger + 1
      return(Math.max(lDepth, rDepth) + 1);
    }
  }

  private boolean isBTBalanced() {
    return isBTBalanced(root);
  }

  public void bfs(Node root) {
    Queue<Node> level = new LinkedList<Node>();
    if (root == null)
      return;
    level.clear();
    level.add(root);
    while (!level.isEmpty()) {
      Node node = level.remove();
      System.out.print(node.data + " ");
      if (node.left != null)
        level.add(node.left);
      if (node.right != null)
        level.add(node.right);
    }

  }


  public void levelPrint2(Node node) {
    Queue<Node> cl = new LinkedList<>();
    cl.add(node);
    while (!cl.isEmpty()) {
      int levelNodesLength = cl.size();
      for (int i =0; i < levelNodesLength; i++) {
        Node n = cl.remove();
        if (n.left != null) {
          cl.add(n.left);
        }
        if (n.right != null) {
           cl.add(n.right);
        }

        System.out.print(n.data + " ");
      }

      System.out.println("");
      System.out.println("==========");
    }
  }

  public void levelPrint(Node node) {
    Queue<Node> cl = new LinkedList<>();
    Queue<Node> nl = new LinkedList<>();

    cl.add(node);
    while (!cl.isEmpty()) {
      for (Node n : cl) {

        if (n.left != null) {
          nl.add(n.left);
        }
        if (n.right != null) {
          nl.add(n.right);
        }

        System.out.print(n.data + " ");
      }

      System.out.println("");
      System.out.println("==========");
      cl = nl;
      nl = new LinkedList<>();
    }
  }

  public void printLevelOrder(Node tmpRoot) {

    Queue<Node> currentLevel = new LinkedList<Node>();
    Queue<Node> nextLevel = new LinkedList<Node>();

    currentLevel.add(tmpRoot);

    while (!currentLevel.isEmpty()) {
      Iterator<Node> iter = currentLevel.iterator();
      while (iter.hasNext()) {
        Node currentNode = iter.next();
        if (currentNode.left != null) {
          nextLevel.add(currentNode.left);
        }
        if (currentNode.right != null) {
          nextLevel.add(currentNode.right);
        }
        System.out.print(currentNode.data + " ");
      }
      System.out.println("");
      System.out.println("==============");
      currentLevel = nextLevel;
      nextLevel = new LinkedList<Node>();

    }

  }

  /**
   * Given a tree and a sum, returns true if there is a path from the root
   * down to a leaf, such that adding up all the values along the path
   * equals the given sum.
   * Strategy: subtract the node value from the sum when recurring down,
   * and check to see if the sum is 0 when you run out of tree.
   */
  public boolean hasPathSum(int sum) {
    return(hasPathSum(root, sum));
  }

  public boolean hasPathSumV2(Node n, int sum) {
    if (n == null) {
      return(sum == 0);

    } else {
      boolean leftPath = hasPathSumV2(n.left, sum - n.data);
      boolean rightPath = hasPathSumV2(n.right, sum - n.data);
      return(leftPath || rightPath);
    }

  }

  boolean hasPathSum(Node node, int sum) {
    // return true if we run out of tree and sum==0
    if (node == null) {
      return(sum == 0);
    } else {
      // otherwise check both subtrees
      int subSum = sum - node.data;
      return(hasPathSum(node.left, subSum) || hasPathSum(node.right, subSum));
    }
  }

  boolean containsTree(Node t1, Node t2) {

    if (t2 == null)
      return true; // The empty tree is always a subtree

    else
      return subTree(t1, t2);
  }

  boolean subTree(Node r1, Node r2) {

    if (r1 == null)

      return false; // big tree empty & subtree still not found.

    if (r1.data == r2.data) {

      if (matchTree(r1, r2))
        return true;

    }

    return(subTree(r1.left, r2) || subTree(r1.right, r2));
  }

  // algo:
  // match both subtrees have to match
  // return true from left when null is encountered and no nodes are unequal
  // do same for right subtree
  // AND both results
  boolean matchTree(Node r1, Node r2) {

    if (r2 == null && r1 == null)

      return true; // nothing left in the subtree

    if (r1 == null || r2 == null)

      return false; // big tree empty & subtree still not found

    if (r1.data != r2.data)

      return false; // data doesn’t match

    return(matchTree(r1.left, r2.left) &&

    matchTree(r1.right, r2.right));

  }

  // height of left and right subtree differ by maximum 1

  private boolean isBTBalancedv2(Node node) {
    if (node == null) {
      return true;
    }
    return(Math.abs(heightv2(node.left) - heightv2(node.right)) <= 1);

  }

  private boolean isBTBalanced(Node node) {
    if (node == null) {
      return true;
    }
    return(isBTBalanced(node.left) && isBTBalanced(node.right) && (Math.abs(maxDepth(node.left) - maxDepth(node.right)) <= 1));

  }

  public static void doPostorderTraversalV2(List<Integer> list, Node root) {

    Stack<Node> stack = new Stack<>();
    stack.add(root);
    Node p = root;
    while (!stack.isEmpty() || p != null) {
      if (p != null) {
        stack.push(p);
        p = p.left;
      } else {
        Node n = stack.pop();
        if (stack.peek().left == n) {
          n = stack.peek().right;
        }
      }
    }

  }

  // Iterative for some perspective
  public static void doPostorderTraversal(List<Integer> list, Node root) {
    Stack<Node> stack = new Stack<>();
    Node c = root;
    while (!stack.isEmpty() || c != null) {
      if (c != null) {
        stack.push(c);
        c = c.left;
      } else {
        Node t = stack.pop();
        list.add(t.data);
        // if left child point to right child of parent
        if (!stack.empty() && stack.peek().left == t) {
          c = stack.peek().right;
        }
      }
    }
  }

  public void printPostorder() {
    System.out.println("Post order: ");
    printPostorder(root);
    System.out.println();
  }

  public void printPostorder(Node node) {
    if (node == null)
      return;

    // first recur on both subtrees
    printPostorder(node.left);
    printPostorder(node.right);

    // then deal with the node
    System.out.print(node.data + "  ");
  }

  public static void doInOrderTraversal(List<Integer> list, Node root) {
    Stack<Node> stack = new Stack<>();
    Node p = root;
    while (!stack.isEmpty() || p != null) {
      if (p != null) {
        stack.push(p);
        p = p.left;
      } else {
        Node n = stack.pop();
        list.add(n.data);
        p = n.right;
      }
    }

  }

  public void printTree() {
    System.out.println("In-order: ");
    printTree(root);
    System.out.println();
  }

  private void printTree(Node node) {
    if (node == null)
      return;

    // left, node itself, right
    printTree(node.left);
    System.out.print(node.data + "  ");
    printTree(node.right);
  }

  public static void printPreOrder(Node root) {
    Stack<Node> nodes = new Stack<>();
    Node p = root;
    while (!nodes.isEmpty() || p != null) {
      if (p != null) {
        nodes.push(p);
        p = p.left;
      } else {

      }
    }
  }

  /**
   * Given a binary tree, prints out all of its root-to-leaf
   * paths, one per line. Uses a recursive helper to do the work.
   */
  public void printPaths() {
    int[] path = new int[1000];
    printPaths(root, path, 0);
  }

  /**
   * Recursive printPaths helper -- given a node, and an array containing
   * the path from the root node up to but not including this node,
   * prints out all the root-leaf paths.
   */
  private void printPaths(Node node, int[] path, int pathLen) {
    if (node == null)
      return;

    // append this node to the path array
    path [pathLen] = node.data;
    pathLen++;

    // it's a leaf, so print the path that led to here
    if (node.left == null && node.right == null) {
      printArray(path, pathLen);
    } else {
      // otherwise try both subtrees
      printPaths(node.left, path, pathLen);
      printPaths(node.right, path, pathLen);
    }
  }

  /**
   * Utility that prints ints from an array on one line.
   */
  private void printArray(int[] ints, int len) {
    int i;
    for (i = 0; i < len; i++) {
      System.out.print(ints [i] + " ");
    }
    System.out.println();
  }

  public boolean isBST2() {
    return(isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
  }

  private static boolean isBST(Node root) {
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBST(Node node, int MIN, int MAX) {
    if (node == null)
      return true;
    return(node.data > MIN && node.data < MAX && isBST(node.left, MIN, node.data) && isBST(node.right, node.data, MAX));

  }

  /**
   * Efficient BST helper -- Given a node, and min and max values,
   * recurs down the tree to verify that it is a BST, and that all
   * its nodes are within the min..max range. Works in O(n) time --
   * visits each node only once.
   */
  private boolean isBST2(Node node, int min, int max) {
    if (node == null) {
      return(true);
    } else {
      // left should be in range min...node.data
      boolean leftOk = isBST2(node.left, min, node.data);

      // if the left is not ok, bail out
      if (!leftOk)
        return(false);

      // right should be in range node.data+1..max
      boolean rightOk = isBST2(node.right, node.data + 1, max);

      return(rightOk);
    }
  }

  public void invert() {
    invert(root);
  }

  public void invert(Node n) {
    if (n == null) {
      return;
    }
    invert(n.left);
    invert(n.right);
    Node temp = n.left;
    n.left = n.right;
    n.right = temp;
  }

  public void mirror() {
    System.out.println(" Mirror: ");
    mirror(root);
  }

  private void mirror(Node node) {
    if (node != null) {
      // do the sub-trees
      mirror(node.left);
      mirror(node.right);

      // swap the left/right pointers
      Node temp = node.left;
      node.left = node.right;
      node.right = temp;
    }
  }

  public void doubleTree() {
    doubleTree(root);
  }

  private void doubleTree(Node node) {
    Node oldLeft;

    if (node == null)
      return;

    // do the subtrees
    doubleTree(node.left);
    doubleTree(node.right);

    // duplicate this node to its left
    oldLeft = node.left;
    node.left = new Node(node.data);
    node.left.left = oldLeft;
  }

  public Node sortedArrayToBST(int[] num) {
    if (num.length == 0)
      return null;

    return sortedArrayToBST(num, 0, num.length - 1);
  }

  public Node sortedArrayToBST(int[] num, int start, int end) {
    if (start > end)
      return null;

    int mid = (start + end) / 2;

    Node root = new Node(num [mid]);
    root.left = sortedArrayToBST(num, start, mid - 1);
    root.right = sortedArrayToBST(num, mid + 1, end);

    return root;
  }

  public Node findLca(Node node, int t1, int t2) {
    if (node == null) {
      return null;
    }
    if (node.data > t2 && node.data > t1) {
      // both targets are left
      return findLca(node.left, t1, t2);
    } else if (node.data < t2 && node.data < t1) {
      // both targets are right
      return findLca(node.right, t1, t2);
    } else {
      // either we are diverging or both targets are equal
      // in both cases so we've found the LCA
      // check for actual existence of targets here, if you like
      return node;
    }
  }

  public static void findKthSmallest(Node root, int num) {
    Stack<Node> stack = new Stack<Node>();

    Node current = root;
    int tmp = num;

    while (stack.size() > 0 || current != null) {
      if (current != null) {
        stack.add(current);
        current = current.left;
      } else {
        current = stack.pop();
        tmp--;

        if (tmp == 0) {
          System.out.println(current.data);
          return;
        }

        current = current.right;
      }
    }
  }

  public Node buildTreeFromInAndPostArrays(int[] inorder, int[] postorder) {
    int inStart = 0;
    int inEnd = inorder.length - 1;
    int postStart = 0;
    int postEnd = postorder.length - 1;

    return buildTreeFromInAndPostArrays(inorder, inStart, inEnd, postorder, postStart, postEnd);
  }
  // [0 1 2 3 4 5 6 7 ]
  // 4 2 5 (1) 6 7 3 8 --> in order
  // 4 5 2 6 7 8 3 (1) --> post order

  public Node buildTreeFromInAndPostArrays(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
    if (inStart > inEnd || postStart > postEnd)
      return null;

    int rootValue = postorder [postEnd];
    Node root = new Node(rootValue);
    int k = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder [i] == rootValue) {
        k = i;
        break;
      }
    }
    root.left = buildTreeFromInAndPostArrays(inorder, inStart, k - 1, postorder, postStart, postStart + k - (inStart + 1));
    // Becuase k is not the length, it it need to -(inStart+1) to get the length
    root.right = buildTreeFromInAndPostArrays(inorder, k + 1, inEnd, postorder, postStart + k - inStart, postEnd - 1);
    // postStart+k-inStart = postStart+k-(inStart+1) +1
    return root;
  }

  //////////// 0 1 2 3 4 5 6 7
  // in-order: 4 2 5 (1) 6 7 3 8
  // pre-order: (1) 2 4 5 3 7 6 8
  public Node buildTree(int[] preorder, int[] inorder) {
    int preStart = 0;
    int preEnd = preorder.length - 1;
    int inStart = 0;
    int inEnd = inorder.length - 1;
    return construct(preorder, preStart, preEnd, inorder, inStart, inEnd);
  }

  public Node construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }
    int val = preorder [preStart];
    Node p = new Node(val);
    // find parent element index from inorder
    int k = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (val == inorder [i]) {
        k = i;
        break;
      }
    }
    p.left = construct(preorder, preStart + 1, preStart + (k - inStart), inorder, inStart, k - 1);
    p.right = construct(preorder, preStart + (k - inStart) + 1, preEnd, inorder, k + 1, inEnd);

    return p;
  }

  public void flatten(Node root) {
    Node cur = root;
    while (cur != null) {
      if (cur.left != null) {
        if (cur.right != null) { // if we need to prune a right subtree
          Node next = cur.left;
          while (next.right != null) {
            next = next.right;
          }
          next.right = cur.right;
        }
        cur.right = cur.left;
        cur.left = null;
      }
      cur = cur.right;
    }
  }

  // cut paste left sub to be right sub
  // append right sub to the end of a left node
  // if left node has right children go all theway to the right most child and append right sub tree

  public void flattenv2(Node root) {
    Node current = root;
    while (current != null) {
      if (current.left != null) {

        if (current.right != null) {
          Node next = current.left;
          while (next.right != null) {
            next = next.right;
          }
          next.right = current.right;

        }

        current.right = current.left;
        current.left = null;

      }
      current = current.right;
    }
  }

  // 4
  // 2 5
  // 1 3 6 7
  // ====>
  // 1--2--3--4--5--6--7

  private static void convertIntoDoublyLinkedList(Node root) {
    convert(root, null, null);

  }

  // algorithm: use in order traversal and since we dont need current left pointer
  private static void convert(Node curr, Node head, Node prev) {
    if (curr == null)
      return;
    convert(curr.left, head, prev);
    if (prev == null) {
      head = curr;
    } else {
      curr.left = prev;
      prev.right = curr;
    }
    prev = curr;
    convert(curr.right, head, prev);
  }

  /* Method to calculate the diameter and return it to main */
  int diameter(Node root) {
    /* base case if tree is empty */
    if (root == null)
      return 0;

    /* get the height of left and right sub trees */
    int lheight = height(root.left);
    int rheight = height(root.right);

    /* get the diameter of left and right subtrees */
    int ldiameter = diameter(root.left);
    int rdiameter = diameter(root.right);

    /*
     * Return max of following three
     * 1) Diameter of left subtree
     * 2) Diameter of right subtree
     * 3) Height of left subtree + height of right subtree + 1
     */
    return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));

  }

  static int height(Node node) {
    /* base case tree is empty */
    if (node == null)
      return 0;

    /*
     * If tree is not empty then height = 1 + max of left
     * height and right heights
     */
    return(1 + Math.max(height(node.left), height(node.right)));
  }

  /* A wrapper over diameter(Node root) */
  int diameter() {
    return diameter(root);
  }

  private void countNvalTrees() {
    // Integer nValCount = 0;
    countNvalTreesHelper(root);
    System.out.println(nValCount);

  }

  public boolean countNvalTreesHelper(Node node) {
    if (node == null)
      return true;
    boolean left = countNvalTreesHelper(node.left);
    boolean right = countNvalTreesHelper(node.right);
    if (left && right && (node.left == null || node.left.data == node.data) && (node.right == null || node.right.data == node.data)) {
      nValCount++;
      return true;
    }
    return false;
  }

  public static void main(String[] args) {

    // 5 3 7 2 4 6 8

    BinaryTree bt = new BinaryTree();

    bt.insert(5);
    bt.insert(3);
    bt.insert(7);
    bt.insert(2);
    bt.insert(4);
    bt.insert(6);
    bt.insert(8);

    int size = bt.size();
    System.out.println(" size of tree is " + size);
    bt.printPostorder();
    bt.printTree();
    System.out.println("LEVEL ORDER " );
    bt.levelPrint( bt.root);
    // bt.printPaths();
    // bt.mirror();
    bt.invert();
    System.out.println("After invert");
    bt.levelPrint(bt.root);
    bt.printTree();

    bt.printLevelOrder(bt.sortedArrayToBST(new int[] { 1, 2, 3, 4, 5, 6, 7 }));
    bt.countNvalTrees();
    // System.out.println(" nVal tree count is : " + bt.countNvalTrees());

  }

}