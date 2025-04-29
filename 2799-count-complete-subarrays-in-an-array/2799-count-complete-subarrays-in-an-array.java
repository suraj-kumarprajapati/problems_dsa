class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;

        Set<Integer> unique = new HashSet<>();
        for(int num : nums) {
            unique.add(num);
        }

        int ans = 0;
        int k = unique.size();
        Map<Integer, Integer> freq = new HashMap<>();
        int i = 0;
        for(int j = 0; j < n; j++) {
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);

            while(freq.size() >= k) {
                if(freq.get(nums[i]) == 1)
                    break;
                freq.put(nums[i], freq.get(nums[i]) - 1);
                i += 1;
            }

            if(freq.size() == k)
                ans += i + 1;
        }

        return ans;
    }
}