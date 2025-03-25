class Solution {
    public int getLargestOutlier(int[] nums) {
        int n = nums.length;

        int sum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums) {
            sum += num;
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int out = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int diff = sum - nums[i];

            int temp = diff / 2;
            if(diff % 2 != 0)
                continue;


            if(freq.containsKey(temp)) {
                if(temp == nums[i] && freq.get(nums[i]) >= 2) {
                    out = Math.max(out, nums[i]);
                }
                else if(temp != nums[i]) {
                    out = Math.max(out, nums[i]);
                }
            }
        }

        return out;
    }
}