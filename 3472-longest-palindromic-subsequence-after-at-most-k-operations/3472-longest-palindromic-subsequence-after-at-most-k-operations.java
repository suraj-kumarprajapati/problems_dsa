class Solution {

    int[][][] dp;
    
    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();

        this.dp = new int[n][n][k+1];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(0, n-1, k, s);
    }

    public int solve(int i, int j, int k, String s) {
        // base cases
        if(i == j)
            return 1;

        if(i > j)
            return 0;

    
        if(dp[i][j][k] != -1)
            return dp[i][j][k];


        // 3 possibilities to generate a subsequence from the string

        // first -> skip i
        int len1 = solve(i+1, j, k, s);

        // second -> skip j
        int len2 = solve(i, j-1, k, s);

        // third -> consider i and j and covert i indexed char to j indexed char or vice - verca
        int len3 = 0;

        // third operation is feasible only if the cost for the operation is less than or equal to k 
        int cost = Math.min(Math.abs(s.charAt(i) - s.charAt(j)), 26 - Math.abs(s.charAt(i) - s.charAt(j)));
        if(cost <= k ) {
            len3 = 2 + solve(i+1, j-1, k-cost, s);
        }

        // return the longest length
        return dp[i][j][k] = Math.max(len1, Math.max(len2, len3));

    }
}