class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> prodFreq = new HashMap<>();
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int prod = nums[i] * nums[j];
                prodFreq.put(prod, prodFreq.getOrDefault(prod, 0) + 1);
            }
        }


        int totalTuples = 0;
        for(Map.Entry<Integer, Integer> it : prodFreq.entrySet()) {
            int value = it.getValue();
            if(value >= 2)
                totalTuples += 8 * (value) * (value - 1) / 2;
        }

        return totalTuples;
    }

   

}
