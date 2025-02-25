class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int evenSubSumCount = 1;
        int oddSubSumCount = 0;
        int prefixSum = 0;
        int mod = (int) 1e9 + 7;
        for(int i=0; i<n; i++) {
            prefixSum += arr[i];

            if(prefixSum % 2 == 0) {
                ans = (ans + oddSubSumCount) % mod;
                evenSubSumCount += 1;
            }
            else {
                ans = (ans + evenSubSumCount) % mod;
                oddSubSumCount += 1;
            }
        }

        return ans;
    }
}