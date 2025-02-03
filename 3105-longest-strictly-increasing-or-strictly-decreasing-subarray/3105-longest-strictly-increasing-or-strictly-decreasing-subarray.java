class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;

        // first check for strictly increasing subarray
        int j = 1;
        int max = 1;
        int win = 1;
        while(j < n) {
            while(j < n && nums[j] > nums[j-1]) {
                win += 1;
                max = Math.max(max, win);
                j += 1;
            }

            win = 1;
            j += 1;
        }

        // check for strictly decreasing subarray
        j = 1;
        win = 1;
        while(j < n) {
            while(j < n && nums[j] < nums[j-1]) {
                win += 1;
                max = Math.max(max, win);
                j += 1;
            }

            win = 1;
            j += 1;
        }

        return max;
    }
}