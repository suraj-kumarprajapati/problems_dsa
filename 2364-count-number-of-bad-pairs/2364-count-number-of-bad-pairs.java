class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long totalPairs =  n * (n-1) / 2;

        Map<Integer, Integer> freq = new HashMap<>();
        for(int i=0; i<n; i++) {
            int diff = i - nums[i];
            freq.put(diff, freq.getOrDefault(diff, 0) + 1);
        }

        long goodPairs = 0;
        for(Map.Entry<Integer, Integer> it : freq.entrySet()) {
            int key = it.getKey();
            int val = it.getValue();

            if(val >= 2) {
                goodPairs += (long) val * (val - 1) / 2;
            }
        }
        
        return totalPairs - goodPairs;
    }
}