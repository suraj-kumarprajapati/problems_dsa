class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        for(int i=0; i<n-1; i++) {
            if(nums[i] == nums[i+1]) {
                nums[i] = nums[i+1] * 2;
                nums[i+1] = 0;
            }
        }

        int j = 0;
        for(int i=0; i<n; i++) {
            if(nums[i] != 0) {
                nums[j] = nums[i];
                j += 1;
            }
        }

        while(j < n) {
            nums[j] = 0;
            j += 1;
        }

        return nums;
    }
}