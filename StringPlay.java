import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class StringPlay {

  static int atoi(String s) {
    int number = 0, i;
    int len = s.length();
    for (i = 0; i < len; i++) {
      number = (number * 10) + s.charAt(i) - '0';
      System.out.println(number);
    }
    return number;
  }

  // sandysandy

  // sandy
  // ysand
  // dysan
  boolean isRotation(String s1, String s2) {
    return (s1.length() == s2.length()) && ((s1 + s1).indexOf(s2) != -1);
  }

  public static boolean isPalindrome(char[] word) {
    int i = 0, j = word.length - 1;
    while (j > i) {
      if (word [i] != word [j]) {
        return false;
      }
      i++;
      j--;
    }
    return true;

  }

  public String greatestCommonPrefix(String a, String b) {
    // get the min length
    int minLength = MathProbs.min(a.length(), b.length());
    // as sson as uncommon char return that substr
    for (int i = 0; i < minLength; i++) {
      if (a.charAt(i) != b.charAt(i)) {
        return a.substring(0, i);
      }
    }
    return a.substring(0, minLength);
  }

  // S = “abacdfgdcaba”, S’ = “abacdgfdcaba”.
  // abab aba

  // public static String findLongestPalindrome(String inputString) {
  //
  // // malayalam -
  // // camabd
  // char[] letters = inputString.toCharArray();
  // int i = 0, j = inputString.length();
  // while (j > i) {
  // if (letters [i] != letters [j]) {
  // j--;
  //
  // }
  //
  // }
  //
  // for (char c : inputString.toCharArray()) {
  //
  // }
  // isPalindrome(inputString);
  // }

  public static void findMaxFrequency(String s) {
    String[] strings = s.split(" ");
    // put word frequencies by tree order
    if (strings.length > 0) {
      Map<String, Integer> wordMap = new HashMap<String, Integer>();

      for (String s1 : strings) {
        if (wordMap.containsKey(s1)) {
          int n = wordMap.get(s1);
          wordMap.put(s1, ++n);
        } else {
          wordMap.put(s1, 1);

        }
      }

      List<Entry<String, Integer>> sortedList = sortByValue(wordMap);

      // int maxRep = -1;

      // for (String s2 : wordMap.keySet()) {
      // if (wordMap.get(s2) > maxRep) {
      // maxRep = wordMap.get(s2);
      // words = s2;
      // }
      // }
    }
  }

  public static List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

    Set<Entry<String, Integer>> set = wordMap.entrySet();
    List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return (o2.getValue()).compareTo(o1.getValue());
      }
    });
    return list;
  }

  public static Character findFirstNonRepeatedCharacter(String s) {

    // ssaadl
    Set<Character> nonRepeatedSet = new LinkedHashSet<>();
    for (char c : s.toCharArray()) {
      if (!nonRepeatedSet.add(c)) {
        nonRepeatedSet.remove(c);
      }
    }
    return(!nonRepeatedSet.isEmpty() ? nonRepeatedSet.iterator().next() : null);

  }

  public static int findDistanceBetweenWordsUnorderedv2(String inputBody, String word1, String word2) {

    String[] strings = inputBody.split(" ");
    int index1 = -1, index2 = -1;
    int minDistanceSoFar = Integer.MAX_VALUE, minDistanceHere = 0;
    for (int x = 0; x < strings.length; x++) {
      if (strings [x].equals(word1)) {
        index1 = x;
      }
      if (strings [x].equals(word2)) {
        index2 = x;
      }
      if (index1 != -1 && index2 != -1) { // both words have to be found
        minDistanceHere = MathProbs.abs(index2 - index1);
        minDistanceSoFar = MathProbs.min(minDistanceHere, minDistanceSoFar);
      }
    }
    System.out.println("Distance:\t" + minDistanceSoFar);
    return minDistanceSoFar;

  }

  public static int findDistanceBetweenWordsv2(String inputBody, String word1, String word2) {
    int minDistanceHere = 0, minDistanceSofar = Integer.MAX_VALUE;
    if (inputBody.isEmpty() || word1.isEmpty() || word2.isEmpty()) {
      return -1;
    }
    if (word1.equals(word2)) {
      return 0;
    }
    String[] strings = inputBody.split(" ");
    boolean foundS1 = false;
    // kadane - start sub array from poi
    for (int i = 0; i < strings.length; i++) {
      if (strings [i].equals(word1)) {
        // reset distance from here
        minDistanceHere = 0;
        foundS1 = true;
      } else if (strings [i].equals(word2) && foundS1) {
        minDistanceSofar = MathProbs.min(minDistanceHere, minDistanceSofar);
      }
      minDistanceHere++;
    }
    if (minDistanceSofar == Integer.MAX_VALUE || minDistanceSofar == 0) {
      return -1;
    } else {
      return minDistanceSofar;
    }
  }

  public static int findDistanceBetweenWords(String inputBody, String pair1, String pair2) {
    if (inputBody.isEmpty() || pair1.isEmpty() || pair2.isEmpty()) {
      return -1;
    }
    if (pair1.equals(pair2)) {
      return 0;
    }
    StringTokenizer stringTokenizer = new StringTokenizer(inputBody, " ");
    int distance = 0, globalDistance = Integer.MAX_VALUE;
    String token;
    boolean foundS1 = false;

    while (stringTokenizer.hasMoreTokens()) {
      token = stringTokenizer.nextToken();
      if (token.equals(pair1)) {
        distance = 0;
        foundS1 = true;

      } else if (token.equals(pair2) && foundS1) {

        globalDistance = MathProbs.min(distance, globalDistance);

      }

      distance++;

    }
    if (globalDistance == Integer.MAX_VALUE) {
      return -1;
    } else {
      return globalDistance;
    }
  }

  public static int findDistanceBetweenWordsUnOrdered(String inputBody, String pair1, String pair2) {
    if (inputBody.isEmpty() || pair1.isEmpty() || pair2.isEmpty()) {
      return -1;
    }
    if (pair1.equals(pair2)) {
      return 0;
    }
    StringTokenizer stringTokenizer = new StringTokenizer(inputBody, " ");
    int distance = 0, globalDistance = Integer.MAX_VALUE;
    String previous = "";
    while (stringTokenizer.hasMoreTokens()) {
      String token = stringTokenizer.nextToken();
      if (previous.isEmpty()) {
        if (token.equalsIgnoreCase(pair1) || token.equalsIgnoreCase(pair2)) {
          previous = token;
          distance = 0;
        }
      } else if (token.equalsIgnoreCase(pair1) || token.equalsIgnoreCase(pair2)) {
        if (!token.equalsIgnoreCase(previous)) {
          globalDistance = Math.min(globalDistance, distance);
          previous = token;
        }
        distance = 0;
      }
      distance++;
    }
    // None of the pairs were found in inputBody.
    if (previous.isEmpty() || globalDistance == Integer.MAX_VALUE) {
      return -1;
    }
    return globalDistance;

  }

  // bob
  // abbccdde
  // abcddcbeb
  // abcddcbebxxb

  public static String longestPalindrome2(String s) {
    if (s == null)
      return null;

    if (s.length() <= 1)
      return s;

    int maxLen = 0;
    String longestStr = null;

    int length = s.length();

    int[][] table = new int[length][length];

    // every single letter is palindrome
    for (int i = 0; i < length; i++) {
      table [i] [i] = 1;
    }
    printTable(table);

    // e.g. bcba
    // two consecutive same letters are palindrome
    for (int i = 0; i <= length - 2; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        table [i] [i + 1] = 1;
        longestStr = s.substring(i, i + 2);
      }
    }
    printTable(table);
    // condition for calculate whole table
    for (int l = 3; l <= length; l++) {
      for (int i = 0; i <= length - l; i++) {
        int j = i + l - 1;
        if (s.charAt(i) == s.charAt(j) && table [i + 1] [j - 1] == 1) {

          if (l > maxLen)
            longestStr = s.substring(i, j + 1);
          maxLen = l;

        } else {
          table [i] [j] = 0;
        }
        printTable(table);
      }
    }

    return longestStr;
  }

  public static void printTable(int[][] x) {
    for (int[] y : x) {
      for (int z : y) {
        System.out.print(z + " ");
      }
      System.out.println();
    }
    System.out.println("------");
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // System.out.println(findDistanceBetweenWords("hello how are you hello you", "hello", "you"));
    // System.out.println(findDistanceBetweenWords("hello how are you hello", "how", "you"));
    // System.out.println(findDistanceBetweenWords("hello how are", "hello", "you"));
    System.out.println(findDistanceBetweenWords("you are how hello", "are", "yo"));
    // System.out.println(findDistanceBetweenWordsUnOrdered("you are how hello a c you hello", "hello", "you"));
    // System.out.println(findDistanceBetweenWordsUnOrdered("you are how hello a c you hello", "you", "hello"));
    // System.out.println(findDistanceBetweenWordsUnOrdered("you are how hello", "you", "hello"));
    // System.out.println(findDistanceBetweenWordsUnOrdered("you are how hello", "you", "a"));
    System.out.println("first non repeated char :" + findFirstNonRepeatedCharacter("maaall"));

    atoi("645");
  }

}
