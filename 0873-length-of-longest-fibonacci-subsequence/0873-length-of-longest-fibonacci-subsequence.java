class Solution {
    Map<Integer, Integer> numToInd;
    int[][] dp;
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        numToInd = new HashMap<>();
        for(int i=0; i<n; i++) {
            numToInd.put(arr[i], i);
        }

        dp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                dp[i][j] = -1;
            }
        }

        int max = 0;
        for(int i = n-1; i >= 2; i --) { 
            for(int j=i-1; j>=1; j--) {
                max = Math.max(max, 2 + fun(i, j, arr));
            }
        }

        if(max <= 2)
            return 0;
        return max;
    }

    public int fun(int ind1, int ind2, int[] arr) {
        if(ind1 <= 1) {
            return 0;
        }

        if(dp[ind1][ind2] != -1)
            return dp[ind1][ind2];


        if(!numToInd.containsKey(arr[ind1] - arr[ind2]))
            return 0;

        int i = numToInd.get(arr[ind1] - arr[ind2]);
        int seqCountTillInd2 = i < ind2 ? 1 + fun(ind2, i, arr) : 0;

        return dp[ind1][ind2] = seqCountTillInd2;
    }
}