class Solution {
    int[] numsArr;
    int[] targetArr;
    int n;
    int m;
    int k;
    int[][] dp;
    long[] lcm;

    public int minimumIncrements(int[] nums, int[] target) {
        this.numsArr = nums;
        this.targetArr = target;
        this.n = nums.length;
        this.m = target.length;
        this.k = (1<<m);  // 2^m
        this.lcm = new long[k];

        this.dp = new int[n][k];
        for(int i=0; i<n; i++) {
            for(int j=0; j<k; j++) {
                dp[i][j] = -1;
            }
        }


        precomputeLCM();

        return solve(0, 0);
    }

    public void precomputeLCM() {
        lcm[0] = 1;
        for(int subset=1; subset<k; subset++) {
            lcm[subset] = 1;

            for(int leftShift=0; leftShift<m; leftShift++) {
                if( (subset & (1 << leftShift)) != 0  ) {
                    lcm[subset] = findLCM(lcm[subset], (long) targetArr[leftShift]);
                }
            }
        }
    }

    public long findLCM(long a, long b) {
        long gcd = findGCD(a, b);
        return (long) ( a/gcd) * b ;
    }

    public long findGCD(long a, long b) { 
        if(a<b) {
            // swap a, b
            long temp = a;
            a = b;
            b = temp;
        }

        while(b != 0) {
            long rem = a%b;
            a = b;
            b = rem;
        }

        return a;
    }


    // using bit masking dp
    public int solve(int ind, int mask) {
        // if this solution is possible, return min value or 0
        if(mask == k-1) 
            return 0;

        // if this solution is not possible, return max value or infinity
        if(ind == n)
            return Integer.MAX_VALUE;

        if(dp[ind][mask] != -1) {
            return dp[ind][mask];
        }
        
        int minOperations = Integer.MAX_VALUE;

        // don't include the current ind
        minOperations = Math.min(minOperations, solve(ind+1, mask));

        // include the current ind
        for(int subset=1; subset < k; subset++) {
            int subLCM = (int) lcm[subset];
            int diff = (int) Math.ceil((double)numsArr[ind] / (double) subLCM ) * subLCM;
            diff = diff - numsArr[ind];

            if((subset & mask) == 0) {
                int temp = solve(ind+1, (subset | mask));
                if(temp != Integer.MAX_VALUE)
                    minOperations = Math.min(minOperations, diff + temp);
            }
        }

        return dp[ind][mask] = minOperations;
    }
}