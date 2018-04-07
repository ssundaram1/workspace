import java.util.*;

/**
 * Created by ssundaram on 3/30/18.
 */
public class Arrs {

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private static int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo+hi)/2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

    public static  int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private static class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }



    //Example 2:
//    Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
//
//    This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
    private static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            result.add(intervals.get(i++));
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval( // we could mutate newInterval here also
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.size()) result.add(intervals.get(i++));
        return result;
    }

//    test(1, "123", true);
//    test(2, " 123 ", true);
//    test(3, "0", true);
//    test(4, "0123", true);  //Cannot agree
//    test(5, "00", true);  //Cannot agree
//    test(6, "-10", true);
//    test(7, "-0", true);
//    test(8, "123.5", true);
//    test(9, "123.000000", true);
//    test(10, "-500.777", true);
//    test(11, "0.0000001", true);
//    test(12, "0.00000", true);
//    test(13, "0.", true);  //Cannot be more disagree!!!
//    test(14, "00.5", true);  //Strongly cannot agree
//    test(15, "123e1", true);
//    test(16, "1.23e10", true);
//    test(17, "0.5e-10", true);
//    test(18, "1.0e4.5", false);
//    test(19, "0.5e04", true);
//    test(20, "12 3", false);
//    test(21, "1a3", false);
//    test(22, "", false);
//    test(23, "     ", false);
//    test(24, null, false);
//    test(25, ".1", true); //Ok, if you say so
//    test(26, ".", false);
//    test(27, "2e0", true);  //Really?!
//    test(28, "+.8", true);
//    test(29, " 005047e+6", true);  //Damn = =|||


    private static List<String> fullJustify(String[] words, int L) {
        List<String> list = new LinkedList<String>();

        for (int i = 0, w; i < words.length; i = w) {
            int len = -1;
            for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
                len += words[w].length() + 1;
            }

            StringBuilder strBuilder = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != words.length) { // not 1 char, not last line
                space = (L - len) / (w - i - 1) + 1;
                extra = (L - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) strBuilder.append(' ');
                if (extra-- > 0) strBuilder.append(' ');
                strBuilder.append(words[j]);
            }
            int strLen = L - strBuilder.length();
            while (strLen-- > 0) strBuilder.append(' ');
            list.add(strBuilder.toString());
        }

        return list;
    }

    private static List<String> textJustified(String[] words, int maxWidth){
        List<String> resultList = new ArrayList<>();
        for(int startWordIdx =0, currWordIdx; startWordIdx< words.length;startWordIdx= currWordIdx ){
            int currWidth = -1;
            //find max words to fit in a line so find the last idx which is value of  currIdx  after loop
            for(currWordIdx =startWordIdx; currWordIdx < words.length && (currWidth + words[currWordIdx].length()) +1 <= maxWidth ; currWordIdx++ ){
                currWidth = (currWidth + words[currWordIdx].length()) +1;
            }
            ;
            int spacesToFill = maxWidth - currWidth,  spaceBetweenWords = (currWordIdx - startWordIdx) -1,  spaceToEvenlyDistribute = 1,  remainderSpaceToDist = 0;
            StringBuilder sb = new StringBuilder(words[startWordIdx]);
            if(currWordIdx != startWordIdx +1 || currWordIdx != words.length){
                spaceToEvenlyDistribute = spacesToFill/spaceBetweenWords + 1;
                remainderSpaceToDist = spacesToFill % spaceBetweenWords;
            }
            for(int j = startWordIdx+1; j< currWordIdx ;j ++ ){
                for(int k =0; k < spaceToEvenlyDistribute;k++){
                    sb.append(' ');
                }
                if(remainderSpaceToDist > 0){
                    sb.append(' ');
                    remainderSpaceToDist--;
                }
                sb.append(words[j]);
            }
            int remaining = maxWidth-sb.length();
            while(remaining > 0) {
                sb.append(' ');
                remaining--;
            }
            resultList.add(sb.toString());
        }
        return resultList;

    }

    private static int minCostII(int[][] costs) {
        if(costs==null || costs.length==0)
            return 0;
        int preMin=0;
        int preSecond=0;
        int preIndex=-1;
        for(int i=0; i<costs.length; i++){
            int currMin=Integer.MAX_VALUE;
            int currSecond = Integer.MAX_VALUE;
            int currIndex = 0;
            for(int j=0; j<costs[0].length; j++){
                if(preIndex==j){
                    costs[i][j] += preSecond;
                }else{
                    costs[i][j] += preMin;
                }
                if(currMin>costs[i][j]){
                    currSecond = currMin;
                    currMin=costs[i][j];
                    currIndex = j;
                } else if(currSecond>costs[i][j] ){
                    currSecond = costs[i][j];
                }
            }
            preMin=currMin;
            preSecond=currSecond;
            preIndex =currIndex;
        }
        int result = Integer.MAX_VALUE;
        for(int j=0; j<costs[0].length; j++){
            if(result>costs[costs.length-1][j]){
                result = costs[costs.length-1][j];
            }
        }
        return result;
    }


    public interface NestedInteger
    {
        // Returns true if this NestedInteger holds a single integer, rather than a nested list
        public boolean isInteger();

        // Returns the single integer that this NestedInteger holds, if it holds a single integer
// Returns null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Returns the nested list that this NestedInteger holds, if it holds a nested list
// Returns null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    private static int  depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }
    private static int  helper(List<NestedInteger> nl, int depth) {
        int res = 0;
        for (NestedInteger a : nl) {
            res += a.isInteger() ? a.getInteger() * depth : helper(a.getList(), depth + 1);
        }
        return res;
    }

    private static int  inverseDepthSum(List<NestedInteger> nestedList) {
        Map<Integer, Integer> levelsToInt = new HashMap<>();
         inverseHelper(nestedList, 0, levelsToInt );
         int sum =0;
         int i =0;
         for(Map.Entry<Integer,Integer> entry : levelsToInt.entrySet()){
             sum+= levelsToInt.get(entry.getValue()) * (levelsToInt.size() - entry.getKey());
         }


    }
    private static void  inverseHelper(List<NestedInteger> nl, int level, Map<Integer, Integer> levelToInt) {
        for (NestedInteger nestedInteger : nl) {
            if(nestedInteger.isInteger()){
                levelToInt.put(level, levelToInt.getOrDefault(level, 0)+nestedInteger.getInteger());
            }
            inverseHelper(nestedInteger.getList(), level+1, levelToInt );
        }
    }



    public static boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen)
                    return false;
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen)
                    return false;
                numberSeen = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e')
                    return false;
            } else
                return false;
        }
        return numberSeen;
    }


    //Given [1,3],[2,6],[8,10],[15,18],
    //return [1,6],[8,10],[15,18].
    private static List<Interval> mergeIntvl(List<Interval> intervals){
        Collections.sort(intervals,  (o1,o2)-> Integer.compare(o1.start, o2.start) );
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> mergedIntervals = new ArrayList<>();
        for(Interval interval : intervals){
            if(interval.start <= end){
                end = Math.max(end,interval.end); // Overlapping intervals, move the end if needed
            }else{
                // Disjoint intervals, add the previous one and reset bounds
                mergedIntervals.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        mergedIntervals.add(new Interval(start, end));
        return mergedIntervals;

    }



    public static void main(String[] args){

        List<String> justifiedText = textJustified(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        System.out.println("justified text biatch");
        justifiedText.forEach((e)-> System.out.println(e));
       ;
        justifiedText = fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        System.out.println("Full justified text biatch");
        justifiedText.forEach((e)-> System.out.println(e));
        ;
        depthSum({{1,1}, 2, {1,1}});

    }
}
