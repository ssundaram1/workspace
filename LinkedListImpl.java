public class LinkedListImpl {

  private Node head;

  private class Node {
    int data;
    Node next;

    public Node(int data) {
      this.data = data;

    }

  }

  public Node reverseLinkedList(Node head) {
    Node prev = null;
    Node curr = head;
    Node next = null;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;

    }
    head = prev;
    return head;

  }

  public Node groupOddEvenLL(Node head) {
    Node newHead = head;
    Node oddHead = head;
    Node evenHead = head.next;
    Node connect = head.next;
    while (oddHead != null && evenHead != null && evenHead.next != null) {
      oddHead.next = evenHead.next;
      oddHead = oddHead.next;

      evenHead.next = oddHead.next;
      evenHead = evenHead.next;

    }
    oddHead.next = connect;
    return newHead;

  }

  public void displayLinkedlist(Node head) {
    System.out.println("");

    System.out.print("Head -->");

    while (head != null) {
      System.out.print(" -> ");
      System.out.print(head.data);
      head = head.next;
    }
  }

  public Node findLoopStart(Node headerLink) {
    Node slowLink = headerLink, fastLink = headerLink;
    if (headerLink == null) {
      return null;
    }
    while (true) {
      slowLink = slowLink.next;
      if (fastLink.next != null) {
        fastLink = fastLink.next.next;
      } else {
        return null;
      }
      // slowLink and fastLink collide
      if (slowLink == fastLink) {
        break;
      }
      if (slowLink == null || fastLink == null) {
        return null;
      }
    }
    // move slowLink to headerLink
    slowLink = headerLink;

    // Now move slowLink as well as fastLink by one jump at a time.
    // The collision point is the start of the loop.
    while (true) {
      if (slowLink == fastLink) {
        return slowLink;
      }
      slowLink = slowLink.next;
      fastLink = fastLink.next;
    }
  }

  public boolean hasLoop(Node headerLink) {
    Node slowLink = headerLink, fastLink = headerLink;
    if (headerLink == null) {
      return false;
    }
    while (true) {
      slowLink = slowLink.next;
      if (fastLink.next != null) {
        fastLink = fastLink.next.next;
      } else {
        return false;
      }
      if (slowLink == fastLink) {
        return true;
      }
      if (slowLink == null || fastLink == null) {
        return false;
      }
    }
  }

  public static void main(String[] args) {

    LinkedListImpl ll = new LinkedListImpl();

    Node n1 = ll.new Node(1);
    Node n2 = ll.new Node(2);
    Node n3 = ll.new Node(3);
    Node n4 = ll.new Node(4);
    Node n5 = ll.new Node(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    ll.displayLinkedlist(n1);
    ll.displayLinkedlist(ll.groupOddEvenLL(n1));
    ll.displayLinkedlist(ll.reverseLinkedList(n1));

  }

}
