class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        // use kadane's algorithm twice

        int max1 = findMaxSubSum(nums);

        for(int i=0; i<n; i++) {
            nums[i] = -1*nums[i];
        }

        int max2 = findMaxSubSum(nums);

        for(int i=0; i<n; i++) {
            nums[i] = -1*nums[i];
        }

        return Math.max(max1, max2);      
    }

    public int findMaxSubSum(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<n; i++) {
            if(sum < 0) {
                sum = 0;
            }

            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}