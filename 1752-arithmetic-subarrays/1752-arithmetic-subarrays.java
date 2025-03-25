class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        int n = nums.length;
        int m = l.length;

        for(int i=0; i<m; i++) {
            int left = l[i];
            int right = r[i];
            int len = right - left + 1;
            Map<Integer, Integer> freq = new HashMap<>();

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int j=left; j<=right; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
            }

            if((max-min) % (len-1) != 0) {
                ans.add(false);
                continue;
            }

            int d = (max - min) / (len-1);

            boolean currAns = true;
            for(int j=0; j<=len-1; j++) {
                int next = min + j*d;

                if(!freq.containsKey(next)) {
                    currAns = false;
                    break;
                }

                freq.put(next, freq.get(next) - 1);
                if(freq.get(next) == 0)
                    freq.remove(next);
            }

            ans.add(currAns);
        }

        return ans;
    }
}