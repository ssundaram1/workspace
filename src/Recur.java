import java.util.*;

/**
 * Created by ssundaram on 1/3/18.
 */
//https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
public class Recur {

    private  static void comboSum(int[] candidates, int sum){
        Set<List<Integer>> resultSet = new HashSet<>();
        Arrays.sort(candidates);
        comboHelper(0,candidates, resultSet, new ArrayList<>(), sum);
        System.out.println("result set:");
        System.out.println(resultSet);
    }

    private static void comboHelper(int start, int[] candidates, Set<List<Integer>> resultSet, List<Integer> current, int target){

        if(target == 0){
            List<Integer> temp = new ArrayList<>(current);
            resultSet.add(temp);
            return;
        }

        for(int i =start; i < candidates.length; i++){
            if(i > target){
                return;
            }
            current.add(candidates[i]);
            comboHelper(i+1, candidates, resultSet, current, target - candidates[i]);
            current.remove(current.size() - 1);
        }
    }



    public static List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        dfs(result, "", n, n);
        return result;
    }
    /*
    left and right represents the remaining number of ( and ) that need to be added.
    When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops.
    */
    public static void dfs(ArrayList<String> result, String s, int left, int right){
        if(left > right)
            return;

        if(left==0&&right==0){
            result.add(s);
            return;
        }

        if(left>0){
            dfs(result, s+"(", left-1, right);
        }

        if(right>0){
            dfs(result, s+")", left, right-1);
        }
    }

    public static void maxSubSeq(int[] n){

        int longestSS = 1;
        int[] longestSSUntilPoint = new int[n.length];
        Arrays.fill(longestSSUntilPoint, 1);
        for(int i=1; i< n.length;i++){
            int currentVal = calculateLongestSS(i, n, longestSSUntilPoint);
            if(currentVal > longestSS){
                longestSS = currentVal;
            }
        }
        System.out.println("longestSS "+ longestSS);
    }

    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs( char[] X, char[] Y, int m, int n )
    {
        if (m == 0 || n == 0)
            return 0;
        if (X[m-1] == Y[n-1])
            return 1 + lcs(X, Y, m-1, n-1);
        else
            return Math.max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
    }


    /* Dynamic Programming Java implementation of LCS problem */
        /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
      private static int longestCommonSub( char[] X, char[] Y, int m, int n ) {
            int L[][] = new int[m+1][n+1];

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
            for (int i=0; i<=m; i++)
            {
                for (int j=0; j<=n; j++)
                {
                    if (i == 0 || j == 0)
                        L[i][j] = 0;
                    else if (X[i-1] == Y[j-1])
                        L[i][j] = L[i-1][j-1] + 1;
                    else
                        L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
            return L[m][n];
        }



    private static int calculateLongestSS(int i, int[] n, int[] longestSSUntilPoint){
        if(i==0){
            return 1;
        }
        int longestSSUntilHere = 1;
        for(int j =0; j< i; j++){
            if(n[j] < n[i] && longestSSUntilPoint[j]+1 > longestSSUntilPoint[i] ){
                longestSSUntilPoint[i] = longestSSUntilPoint[j]+1;
            }

        }
        return longestSSUntilPoint[i];
    }

    /* lis() returns the length of the longest increasing
     subsequence in arr[] of size n */
    static int lis(int arr[],int n)
    {
        int lis[] = new int[n];
        int i,j,max = 0;

          /* Initialize LIS values for all indexes */
        for ( i = 0; i < n; i++ )
            lis[i] = 1;

           /* Compute optimized LIS values in bottom up manner */
        for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ )
                if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

           /* Pick maximum of all LIS values */
        for ( i = 0; i < n; i++ )
            if ( max < lis[i] )
                max = lis[i];

        return max;
    }

    static int min(int x,int y,int z) {
        if (x<=y && x<=z) return x;
        if (y<=x && y<=z) return y;
        else return z;
    }


    private static int coinChangeRecurDP(int[] coins, int m, int sum){

        //Time complexity of this function: O(mn)
        //Space Complexity of this function: O(n)

        // table[i] will be storing the number of solutions
        // for value i. We need n+1 rows as the table is
        // constructed in bottom up manner using the base
        // case (n = 0)
        long[] table = new long[sum+1];

        // Initialize all table values as 0
        Arrays.fill(table, 0);   //O(n)

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int i=0; i<m; i++)
            for (int j=coins[i]; j<=sum; j++)
                table[j] += table[j-coins[i]];

        return (int) table[sum];


    }



    private static int coinChangeRecurHelper(int[] coins, int sum){
        return coinChangeRecur(coins, coins.length, sum);

    }

    private static int coinChangeRecurHelperDP(int[] coins, int sum){
        return coinChangeRecurDP(coins, coins.length, sum);

    }

    private static int coinChangeRecur(int[] coins, int m, int sum){

        //base cases
        if(sum < 0 ){
            return 0;
        }
        //if sum is 0 1 solution of empty set no coins
        if(sum ==0){
            return 1;
        }
        if(m <=0 && sum >=0){
            return 0;
        }
        return coinChangeRecur(coins, m-1, sum)+coinChangeRecur(coins,m, sum-coins[m-1]);


    }


//    Let's consider str1 = "geek" and str2 = "ges"
//    When you compare last characters of str1(i = 4) and str2(j = 3) and they don't match, you can do one of these :-
//
//    Insert : If you insert a character at the end of str1, it becomes "geeks" (s has been inserted), so you still have to edit "geek" (upto i = 4) to reach "ge" (upto j = 2)
//    This is the case of (i, j-1)
//
//    Delete : If you delete the last character of str1, it becomes "gee" (k has been deleted). You still have to edit "gee" (upto i = 3) to reach "ges" (upto j = 3)
//    This is the case of (i-1, j)
//
//    Replace : If you replace the last character of str1 with last char of str2, it becomes "gees" (k replaced by s). You still have to edit "gee" (upto i = 3) to reach "ge" (upto j = 2)
//    This is the case of (i-1, j-1)


    static int editDist(String str1 , String str2 , int m ,int n) {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0) return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0) return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if (str1.charAt(m-1) == str2.charAt(n-1))
            return editDist(str1, str2, m-1, n-1);

        // If last characters are not same, consider all three
        // operations on last character of first string, recursively
        // compute minimum cost for all three operations and take
        // minimum of three values.
        return 1 + min ( editDist(str1,  str2, m, n-1),    //
                editDist(str1,  str2, m-1, n),   // Remove
                editDist(str1,  str2, m-1, n-1) // Replace
        );
    }



    static int editDistDP(String str1, String str2, int m, int n)
    {
        // Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];

        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j

                    // If second string is empty, only option is to
                    // remove all characters of first string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last char
                    // and recur for remaining string
                else if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];

                    // If last character are different, consider all
                    // possibilities and find minimum
                else
                    dp[i][j] = 1 + min(dp[i][j-1],  // Insert
                            dp[i-1][j],  // Remove
                            dp[i-1][j-1]); // Replace
            }
        }

        return dp[m][n];
    }



    // A Recursive java program to solve
// minimum sum partition problem.
        // Returns the minimum value of
        //the difference of the two sets.
        static int findMin(int arr[], int n)
        {
            // Calculate sum of all elements
            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += arr[i];

            // Create an array to store
            // results of subproblems
            boolean dp[][] = new boolean[n + 1][sum + 1];

            // Initialize first column as true.
            // 0 sum is possible  with all elements.
            for (int i = 0; i <= n; i++)
                dp[i][0] = true;

            // Initialize top row, except dp[0][0],
            // as false. With 0 elements, no other
            // sum except 0 is possible
            for (int i = 1; i <= sum; i++)
                dp[0][i] = false;

            // Fill the partition table
            // in bottom up manner
            for (int i = 1; i <= n; i++)
            {
                for (int j = 1; j <= sum; j++)
                {
                    // If i'th element is excluded
                    dp[i][j] = dp[i - 1][j];

                    // If i'th element is included
                    if (arr[i - 1] <= j)
                        dp[i][j] |= dp[i - 1][j - arr[i - 1]];
                }
            }

            // Initialize difference of two sums.
            int minDiff = Integer.MAX_VALUE;

            // Find the largest j such that dp[n][j]
            // is true where j loops from sum/2 t0 0
//            for (int j = sum / 2; j >= 0; j--)
//            {
//                // Find the
//                if (dp[n][j] == true)
//                {
//                    diff = sum - 2 * j;
//                    break;
//                }
//            }

            for(int j = sum/2; j<=sum -1; j++){

                 //Find the
                if (dp[n][j] == true)
                {
                    int diff = sum - j;
                    if(diff < minDiff){
                        minDiff = diff;
                    }
                    //break;
                }
            }
            return minDiff;
        }


// dice where every dice has 'm' faces

    // The main function that returns number of ways to get sum 'x'
// with 'n' dice and 'm' with m faces.
    int findWays(int m, int n, int x)
    {
        // Create a table to store results of subproblems.  One extra
        // row and column are used for simpilicity (Number of dice
        // is directly used as row index and sum is directly used
        // as column index).  The entries in 0th row and 0th column
        // are never used.
        int[][] table= new int[n + 1][x + 1];
        Arrays.fill(table, 0);

        // Table entries for only one dice
        for (int j = 1; j <= m && j <= x; j++)
            table[1][j] = 1;

        // Fill rest of the entries in table using recursive relation
        // i: number of dice, j: sum
        for (int i = 2; i <= n; i++)
            for (int j = 1; j <= x; j++)
                for (int k = 1; k <= m && k < j; k++)
                    table[i][j] += table[i-1][j-k];

    /* Uncomment these lines to see content of table
    for (int i = 0; i <= n; i++)
    {
      for (int j = 0; j <= x; j++)
        cout << table[i][j] << " ";
      cout << endl;
    } */
        return table[n][x];
    }






    public static void main(String[] args){

        comboSum(new int[]{1,2,3,5,6,7}, 10);
        System.out.println(generateParenthesis(2)); ;
        maxSubSeq( new int[]{ 10, 22, 9, 33, 21, 50, 41, 60 });
        System.out.printf(" Min edit dist "+editDist("saturday", "sunday", 3,3));;
        System.out.println( " # of solutions: "+coinChangeRecurHelper(new int[]{1,2,3}, 4));
        System.out.println( " # of solutions: "+coinChangeRecurHelper(new int[]{2,3,5,6}, 10));
        System.out.println( " # of solutions: "+coinChangeRecurHelperDP(new int[]{2,3,5,6}, 10));

        int arr[] = {3, 1, 4, 2, 2};
        int n = arr.length;
        System.out.println ("The minimum difference between 2 sets is "
                + findMin(arr, n));







    }
}
