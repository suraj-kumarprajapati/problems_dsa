class Solution {
    public int numSquares(int n) {

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return sol1(n, dp);
    }

    public int sol1(int n, int[] dp) {
        if(n == 0)
            return 0;

        if(dp[n] != -1)
            return dp[n];

        int ans = n;
        for(int i=1; i*i <= n; i++) {
            int sqr = i*i;
            ans = Math.min(ans, sol1(n - sqr, dp) + 1);
        }

        return dp[n] = ans;
    }
}