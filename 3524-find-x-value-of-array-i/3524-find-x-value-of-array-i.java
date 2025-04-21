class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;

        long[] ans = new long[k];
        long[][] count = new long[n][k];

        nums[0] = nums[0] % k;
        count[0][nums[0]] += 1;

        for(int i=1; i<n; i++) {
            nums[i] = nums[i] % k;
            count[i][nums[i]] += 1;

            for(int x = 0; x < k; x ++) {
                int rem = (int) ((long) (x * nums[i]) ) % k;
                count[i][rem] += count[i-1][x]; 
            }
        }

        for(int x=0; x<k; x++) {
            for(int i=0; i<n; i++) {
                ans[x] += count[i][x];
            }
        }

        return ans;
    }
}