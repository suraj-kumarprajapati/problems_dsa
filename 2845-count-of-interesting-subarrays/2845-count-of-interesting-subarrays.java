class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long res = 0;

        int[] prefixSum = new int[n];
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += nums.get(i) % modulo == k ? 1 : 0;
            prefixSum[i] = sum;
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for(int i=0; i<n; i++) {
            res += (long) cnt.getOrDefault((prefixSum[i]  - k) % modulo, 0);
            cnt.put(prefixSum[i] % modulo, cnt.getOrDefault(prefixSum[i] % modulo, 0) + 1);
        }

        return res;
    }
}