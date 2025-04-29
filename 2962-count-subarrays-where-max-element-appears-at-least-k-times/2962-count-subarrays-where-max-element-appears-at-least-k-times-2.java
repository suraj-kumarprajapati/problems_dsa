class Solution {
    public long countSubarrays(int[] nums, int k) {
        // return countSubarrays1(nums, k);
        return countSubarrays2(nums, k);
    }

    public long countSubarrays2(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;

        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            max = Math.max(max, num);
        }

        List<Integer> maxEleInd = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(nums[i] == max)
                maxEleInd.add(i);

            if(maxEleInd.size() >= k) {
                ans += (long) (maxEleInd.get(maxEleInd.size() - k ) + 1) ;
            }
        }

        return ans;
    }

    public long countSubarrays1(int[] nums, int k) {
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