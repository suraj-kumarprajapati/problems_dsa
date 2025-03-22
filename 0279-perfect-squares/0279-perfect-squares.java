class Solution {
    public int numSquares(int n) {

        // int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);
        // return sol1(n, dp);

        return sol2(n);
    }

    // maths -> using lagrenge and legendres theorem
    // ans will range from 1 to 4
    public int sol2(int n) {
        int sqrtVal = (int) Math.sqrt(n);

        // if ans == 1, then n is perfect square
        if(sqrtVal * sqrtVal == n)
            return 1;
        
        // if ans == 4, then n = 4^k * (8*m + 7)
        int N = n;
        while(N%4 == 0) {
            N = N / 4;
        }

        if(N % 8 == 7)
            return 4;

        // if ans == 2, then i*i*j*j == n
        for(int i=1; i*i <= n; i++) {
            int sqr1 = i*i;
            int ele2 = (int) Math.sqrt(n - sqr1);

            if(ele2 * ele2 == (n - sqr1)) {
                return 2;
            }
        }

        // return 3
        return 3;
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