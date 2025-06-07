class Solution {
    private long[][][] dp;
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        this.dp = new long[n+1][k+1][3];


        for(long[][] dp1 : dp) {
            for(long[] dp2 : dp1) {
                Arrays.fill(dp2, -1);
            }
        }

        long finalAns = Long.MIN_VALUE;
        for(int i=0; i<=k; i++) {
            finalAns = Math.max(finalAns, fun(0, i, 0, prices));
        }

        return finalAns;
    }


    // ind -> current ind
    // rem -> remaining transaction to do
    // lastState -> state of last transaction : 0 -> completed, 1 -> bought, 2 -> sold
    
    // lastState == 0 (completed transaction), at ind -> three options sell, buy, do nothing 
    // lastState == 1 (bought), at ind -> two options -> sell, do nothing
    // lastState == 2 (sold), at ind -> two options -> buy, do nothing
    public long fun(int ind, int rem, int lastState, int[] prices) {
        int n = prices.length;

        // base cases
        // no more transactions left
        if(rem == 0)
            return 0;

        // reached ind 'n' without doing all transactions
        if(ind == n)
            return Long.MIN_VALUE / 2;

        if(dp[ind][rem][lastState] != -1)
            return dp[ind][rem][lastState];

        // last transaction completed
        if(lastState == 0) {
            // three options
            // do nothing
            long p1 = fun(ind+1, rem, lastState, prices);
            // sell -> got some money
            long p2 = prices[ind] + fun(ind+1, rem, 2, prices);
            // buy -> loose some money
            long p3 = -prices[ind] + fun(ind+1, rem, 1, prices);

            return dp[ind][rem][lastState] = Math.max(p1,  Math.max(p2, p3));
        }
        else {
            // two options
            // do nothing
            long p1 = fun(ind+1, rem, lastState, prices);
            
            long p2 = 0;
            // sell if bought earlier and complete the current transaction -> reduce rem
            if(lastState == 1) 
                p2 = prices[ind] + fun(ind+1, rem-1, 0, prices);
            // buy if sold earlier and complete the curretn transaction -> reduce rem
            else
                p2 = -prices[ind] + fun(ind+1, rem-1, 0, prices);

            return  dp[ind][rem][lastState] = Math.max(p1, p2);
        }
    }
}