class Solution {
    public long distributeCandies(int n, int limit) {
        // long[][] dp = new long[n+1][4];
        // for(int i=0; i<=n; i++) {
        //     Arrays.fill(dp[i], -1);
        // }

        // return sol1(n, 0, limit, dp);

        return sol2(n, limit);
    }

    private long sol2(int n, int limit) {

        // for first child considering there are two child child1 = firstChild and child2 = (secondChild + thirdChild)
        int minCandies = Math.max(0, n - 2*limit);
        int maxCandies = Math.min(n, limit);

        long totalWays = 0;
        for(int candies1=minCandies; candies1<=maxCandies; candies1++) {
            // after assigning candies1 to child1
            int remCandies = n - candies1;

            // to distribute remaining candies to child2 and child3
            int min = Math.max(0, remCandies - limit);
            int max = Math.min(remCandies, limit);

            totalWays += (long) (max - min + 1);
        }

        return totalWays;
    }

    // time -> O(n*limit)
    private long sol1(int remCandies, int currentChild, int limit, long[][] dp) {
        if(currentChild == 3 ) {
            return (remCandies == 0) ? 1 : 0;
        }

        if(dp[remCandies][currentChild] != -1)
            return dp[remCandies][currentChild];

        long res = 0;
        for(int i=0; i<=limit; i++) {
            if(remCandies >= i)
                res += sol1(remCandies - i, currentChild + 1, limit, dp);
        }

        return dp[remCandies][currentChild] = res;
    }
}