/**
 * Created by ssundaram on 1/27/18.
 */
public class DP {




//    Gap = 1:
//            (0, 1) (1, 2) (2, 3) (3, 4)
//
//    Gap = 2:
//            (0, 2) (1, 3) (2, 4)
//
//    Gap = 3:
//            (0, 3) (1, 4)
//
//    Gap = 4:
//            (0, 4)

    // A DP function to find minimum number
    // of insersions
    static int findMinInsertionsDP(char str[], int n)
    {
        // Create a table of size n*n. table[i][j]
        // will store minumum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int table[][] = new int[n][n];
        int l, h, gap;

        // Fill the table
        for (gap = 1; gap < n; ++gap)
            for (l = 0, h = gap; h < n; ++l, ++h)
                table[l][h] = (str[l] == str[h])?
                        table[l+1][h-1] :
                        (Integer.min(table[l][h-1],
                                table[l+1][h]) + 1);


        // Return minimum number of insertions
        // for str[0..n-1]
        return table[0][n-1];
    }


    static int minInsertion2(String str)
    {
        int n = str.length();

        // Create a table to store
        // results of subproblems
        int L[][] = new int[n][n];

        // Strings of length 1
        // are palindrome of length 1
        for (int i = 0; i < n; i++)
            L[i][i] = 0;

        // Build the table. Note that the lower diagonal
        // values of table are useless and not filled in
        // the process. c1 is length of substring
        for (int cl=2; cl<=n; cl++)
        {
            for (int i=0; i<n-cl+1; i++)
            {
                int j = i+cl-1;
                L[i][j] = (str.charAt(i) == str.charAt(j))?
                        L[i+1][j-1] :
                        (Integer.min(L[i][j-1],
                                L[i+1][j]) + 1);
                System.out.println(L[i][j]);
            }
        }

        // length of longest palindromic subsequence
        return L[0][n-1];
    }


    // Returns the length of the longest
    // palindromic subsequence in 'str'
    static int lps(String str)
    {
        int n = str.length();

        // Create a table to store
        // results of subproblems
        int L[][] = new int[n][n];

        // Strings of length 1
        // are palindrome of length 1
        for (int i = 0; i < n; i++)
            L[i][i] = 1;

        // Build the table. Note that the lower diagonal
        // values of table are useless and not filled in
        // the process. c1 is length of substring
        for (int cl=2; cl<=n; cl++)
        {
            for (int i=0; i<n-cl+1; i++)
            {
                int j = i+cl-1;
                if (str.charAt(i) == str.charAt(j) && cl == 2)
                    L[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j))
                    L[i][j] = L[i+1][j-1] + 2;
                else
                    L[i][j] = Integer.max(L[i][j-1], L[i+1][j]);
            }
        }

        // length of longest palindromic subsequence
        return L[0][n-1];
    }

    // function to calculate minimum
    // number of deletions
    static int minimumNumberOfDeletions(String str)
    {
        int n = str.length();

        // Find longest palindromic subsequence
        int len = lps(str);

        // After removing characters other than
        // the lps, we get palindrome.
        return (n - len);
    }






    // Driver program to test above function.
    public static void main(String args[])
    {
        String str = "geeks";
        String str1 = "abcd";

        System.out.println(" "+
                findMinInsertionsDP(str1.toCharArray(), str1.length()));
        System.out.println(" "+
                minInsertion2(str1));

        System.out.println("Minimum number of deletions = "
                + minimumNumberOfDeletions("geeksforgeeks"));
    }


}
