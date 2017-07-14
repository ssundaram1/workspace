import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DFSProblems {

  public static List<String> letterCombinations(String digits) {
    HashMap<Character, String> map = new HashMap<>();
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    // map.put(6, "mno");
    // map.put(7, "pqrs");
    // map.put(8, "tuv");
    // map.put(9, "wxyz");
    // map.put(0, "");

    ArrayList<String> result = new ArrayList<String>();

    if (digits == null || digits.length() == 0)
      return result;

    StringBuffer temp = new StringBuffer();
    int level = 0;
    letterComboHelper(digits, temp, result, map, level);

    return result;
  }

  private static void letterComboHelper(String digits, StringBuffer temp, ArrayList<String> result, HashMap<Character, String> map, int level) {
    if (level == digits.length()) {
      result.add(temp.toString());
      return;
    }
    Character digit = digits.charAt(level);
    String alphabets = map.get(digit);
    for (int i = 0; i < alphabets.length(); i++) {
      temp.append(alphabets.charAt(i));
      letterComboHelper(digits, temp, result, map, level + 1);
      temp.deleteCharAt(temp.length() - 1);

    }
  }

  public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    if (candidates == null || candidates.length == 0)
      return result;

    ArrayList<Integer> current = new ArrayList<Integer>();
    Arrays.sort(candidates);

    combinationSum(candidates, target, 0, current, result);

    HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>(result);

    // remove duplicate lists
    result.clear();
    result.addAll(set);
    System.out.println("combo sum not unique:" + result);
    return result;
  }

  public void combinationSum(int[] candidates, int target, int start, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> result) {
    if (target == 0) {
      ArrayList<Integer> temp = new ArrayList<Integer>(curr);
      result.add(temp);
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      if (target < candidates [i])
        return;

      curr.add(candidates [i]);
      combinationSum(candidates, target - candidates [i], i, curr, result);
      curr.remove(curr.size() - 1);
    }
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> list = new ArrayList<Integer>();
    dfs(result, 1, n, list, k);
    return result;
  }

  public void dfs(List<List<Integer>> result, int start, int sum, List<Integer> list, int k) {
    if (sum == 0 && list.size() == k) {
      List<Integer> temp = new ArrayList<Integer>();
      temp.addAll(list);
      result.add(temp);
    }

    for (int i = start; i <= 9; i++) {
      if (sum - i < 0)
        break;
      if (list.size() > k)
        break;

      list.add(i);
      dfs(result, i + 1, sum - i, list, k);
      list.remove(list.size() - 1);
    }
  }

  public void combinationSumUnique(int[] arr, int target) {

    ArrayList<Integer> curr = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    int start = 0;
    Arrays.sort(arr);

    comboHelper(arr, start, curr, target, result);
    HashSet<ArrayList<Integer>> uniqueList = new HashSet<ArrayList<Integer>>();
    uniqueList.addAll(result);
    result.clear();
    result.addAll(uniqueList);

    System.out.println(result);

  }

  private void comboHelper(int[] arr, int start, ArrayList<Integer> curr, int target, ArrayList<ArrayList<Integer>> result) {
    if (target == 0) {
      System.out.println("printing from here" + curr);
      ArrayList<Integer> temp = new ArrayList<Integer>(curr);
      result.add(temp);
    }

    for (int i = 0; i < arr.length; i++) {
      if (target < arr [i]) {
        return;
      }
      curr.add(arr [i]);
      comboHelper(arr, i + 1, curr, target - arr [i], result);
      curr.remove(curr.size() - 1);
    }

  }

  public static List<String> generateParenthesis(int n) {
    ArrayList<String> result = new ArrayList<String>();
    dfs(result, "", n, n);
    return result;
  }

  /*
   * left and right represents the remaining number of ( and ) that need to be added.
   * When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops.
   */

  // (()) ()()
  public static void dfs(ArrayList<String> result, String s, int left, int right) {
    if (left > right)
      return;
    System.out.println("dfs(" + result + ", \"" + s + "\", " + left + ", " + right + ")");
    if (left == 0 && right == 0) {
      result.add(s);
      return;
    }

    if (left > 0) {
      dfs(result, s + "(", left - 1, right);
    }

    if (right > 0) {
      dfs(result, s + ")", left, right - 1);
    }
  }

  public static void main(String[] args) {
    // for 234 : adg adh adi aeg aeh aei afg afh afi bdg bdh bdi beg beh bei bfg bfh bfi cdg cdh cdi ceg ceh cei cfg cfh cfi

    List<String> lc = letterCombinations("234");
    System.out.println(lc.toString());

    // generateParenthesis(2);
    DFSProblems dfsp = new DFSProblems();
    dfsp.combinationSumUnique(new int[] { 1, 2, 3, 45, 6, 7, 8, 9 }, 7);
    // dfsp.combinationSum(new int[] { 1, 2, 3, 45, 6, 7, 8, 9 }, 7);

  }
}
