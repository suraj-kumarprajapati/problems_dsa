class Solution {
    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length;

        long totalProduct = 1;
        for(int num : nums) {
            totalProduct = (long) num * totalProduct ;
        }

        if(target * target != totalProduct)
            return false;

        return sol(0, (long)1, nums, target);
    }

    private boolean sol(int ind, long prod1, int[] nums, long target) {

        int n = nums.length;
        if(prod1 > target)
            return false;

        if(prod1 == target)
            return true;
        
        if(ind == n) {
            return false;
        }


        // option 1 : dont' include in prod1
        boolean res1 = sol(ind+1, prod1, nums, target);

       
        // option 2 : include in prod1
         boolean res2 = sol(ind+1, (long) nums[ind] * prod1, nums, target);

        return res1 || res2;        
    }
}