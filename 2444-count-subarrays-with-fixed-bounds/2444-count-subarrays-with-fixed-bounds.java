class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long ans = 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int minInd = -1;
        int maxInd = -1;
        for(int i=0; i<n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);

            if(nums[i] == minK)
                minInd = i;
            if(nums[i] == maxK)
                maxInd = i;

            if(min == minK && max == maxK  ) {
                ans += (long) (Math.min(minInd, maxInd) + 1);
            }
        }

        return ans;
    }
}