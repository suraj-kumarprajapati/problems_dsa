class Solution {
    public long countGood(int[] nums, int k) {
        long ans = 0;
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<>();

        int l = 0;
        int r = 0;
        long pairs = 0;
        while(r < n) {
            freq.put(nums[r], freq.getOrDefault(nums[r], 0) + 1);
            int f = freq.get(nums[r]);

            if(f >= 2) {
                pairs -= (long) ((f-1) * (f-2) / 2);
                pairs += (long) (f * (f-1) / 2);
            }

            while(l < r && pairs >= k) {
                ans += (long) (n - r);

                f = freq.get(nums[l]);
                freq.put(nums[l], freq.get(nums[l]) - 1);
                
                if(f >= 2) {
                    pairs -= (long) (f * (f-1) / 2);
                    pairs += (long) ((f-1) * (f-2) / 2);
                }

                l += 1;
            }
            r += 1;
        }

        return ans;
    }

   
}