class Solution {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;

        int maxSum = nums[0];
        int sum = nums[0];
        int j = 1;
        while(j < n) {
            while(j < n && nums[j] > nums[j-1]) {
                sum += nums[j];
                j += 1;
            }

            maxSum = Math.max(maxSum, sum);
            if(j<n) sum = nums[j];
            j += 1;
        }

        return maxSum;
    }
}