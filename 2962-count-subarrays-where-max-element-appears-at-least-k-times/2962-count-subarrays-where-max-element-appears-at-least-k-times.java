class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;

        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            max = Math.max(max, num);
        }

        int cnt = 0;
        int i = 0;
        for(int j=0; j<n; j++) {
            if(nums[j] == max)
                cnt += 1;

            while(cnt == k) {
                if(nums[i] == max) {
                    cnt -= 1;
                }
                i += 1;
            }

            ans += (long) (i);
        }

        return ans;        
    }
}