class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        
        if(n == 1)
            return true;

        int count = 0;
        for(int i=0; i<2*n; i++) {
            if(nums[i % n] <= nums[(i+1)%n]) {
                count += 1;
            }
            else {
                count = 1;
            }

            if(count == n) {
                return true;
            }
        }

        return false;
    }
}