import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class TrieNode {
  char c;
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  boolean isLeaf;

  public TrieNode() {
  }

  public TrieNode(char c) {
    this.c = c;
  }
}

public class Trie {
  private final TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    HashMap<Character, TrieNode> children = root.children;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);

      TrieNode t;
      if (children.containsKey(c)) {
        t = children.get(c);
      } else {
        t = new TrieNode(c);
        children.put(c, t);
      }

      children = t.children;

      // set leaf node
      if (i == word.length() - 1)
        t.isLeaf = true;
    }
  }

  // Returns if the word is in the trie.
  public boolean search(String word) {
    TrieNode t = searchNode(word);

    if (t != null && t.isLeaf)
      return true;
    else
      return false;
  }

  public void suggest(String str) {
    TrieNode ref = searchNode(str);
    suggestHelper(str, ref);

  }

  public void suggestHelper(String str, TrieNode node) {
    if (node.isLeaf) {
      System.out.println(str);
      if (node.children.isEmpty()) {
        return;
      }

    }
    for (Entry<Character, TrieNode> entry : node.children.entrySet()) {
      suggestHelper(str + entry.getKey(), entry.getValue());
    }

  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    if (searchNode(prefix) == null)
      return false;
    else
      return true;
  }

  public TrieNode searchNode(String str) {
    Map<Character, TrieNode> children = root.children;
    TrieNode t = null;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (children.containsKey(c)) {
        t = children.get(c);
        children = t.children;
      } else {
        return null;
      }
    }

    return t;
  }

  public static void main(String args[]) {
    Trie trie = new Trie();

    trie.insert("meme");
    trie.insert("me");
    trie.insert("miser");
    trie.insert("memo");

    trie.suggest("m");
    System.out.println("=======================================");
    trie.suggest("me");
    System.out.println("=======================================");
    trie.suggest("mem");
  }
}