class Solution {
    int n;
    int maxValue;
    int[][] dp;
    int mod = (int) (1e9 + 7);


    public int idealArrays(int n, int maxValue) {
        // return sol1(n, maxValue);

        return sol2(n, maxValue);

        // return solCombinatorics(n, maxValue);
    }

    public int solCombinatorics(int n, int maxValue) {
        return 0;
    }

    // using the bottom up approach or tabulation
    public int sol2(int n, int maxValue) {
        this.n = n;
        this. maxValue = maxValue;
        dp = new int[n + 1][maxValue + 1];
        
        // base case
        for(int val=0; val <= maxValue; val++) {
            dp[n][val] = 1;
        }

        for(int ind = n-1; ind >= 0; ind -= 1) {
            for(int currValue = maxValue; currValue >= 1; currValue -= 1) {

                int totalPossible = 0;
                for(int val = currValue; val <= maxValue; val += currValue) {
                    int temp = dp[ind+1][val];
                    totalPossible = (totalPossible + temp) % mod;
                }

                dp[ind][currValue] = totalPossible % mod;
            }
        }

        return dp[0][1];
    }


    // using recursion + memoization
    public int sol1(int n, int maxValue) {
        this.n = n;
        this.maxValue = maxValue;

        dp = new int[n][maxValue + 1];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solMem(0, 1);
    }

    public int solMem(int ind, int currValue) {
        if(ind == n)
            return 1;

        if(dp[ind][currValue] != -1)
            return dp[ind][currValue];

        int totalPossible = 0;
        for(int val = currValue; val <= maxValue; val += currValue) {
            int temp = solMem(ind+1, val);
            totalPossible = (totalPossible + temp) % mod;
        }

        return  dp[ind][currValue] = totalPossible % mod;
    }
}