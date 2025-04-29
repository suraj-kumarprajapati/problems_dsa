class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;

        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            max = Math.max(max, num);
        }

        List<Integer> indices = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(nums[i] == max) {
                indices.add(i);
            }
        }

        int m = indices.size();
        if(m < k)
            return 0;

        int i = 0;
        int j = k-1;
        while(j < m) {
            ans += (long) (indices.get(i) + 1);
            j += 1;
            i += 1;
        }

        return ans;
    }
}