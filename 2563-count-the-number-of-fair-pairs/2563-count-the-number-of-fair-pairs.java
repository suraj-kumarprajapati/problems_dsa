class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        // return sol1(nums, lower, upper);

        return sol2(nums, lower, upper);
    }

    public long sol2(int[] nums, int lower, int upper) {
        int n = nums.length;

        Arrays.sort(nums);
        
        long count = 0;
        for(int i=0; i<n; i++) {
            int curr = nums[i];

            int lowerInd = findLowerInd(nums, curr, lower, i);
            int upperInd = findUpperInd(nums, curr, upper, i);

            if(lowerInd != -1 && upperInd != -1 && lowerInd <= upperInd) {
                count += (long) (upperInd - lowerInd + 1);
            }
        }

        return count;
    }

    private int findLowerInd(int[] nums, int curr, int lower, int i) {
        int n = nums.length;

        int l = i+1;
        int u = n-1;
        int ans = -1;
        while(l <= u) {
            int mid = l + (u - l) / 2;
            int sum = curr + nums[mid];

            if( sum >= lower) {
                ans = mid;
                u = mid - 1;
            }   
            else {
                l = mid + 1;
            }
        }

        return ans;
    }


    private int findUpperInd(int[] nums, int curr, int upper, int i) {
        int n = nums.length;

        int l = i+1;
        int u = n-1;
        int ans = -1;
        while(l <= u) {
            int mid = l + (u - l) / 2;
            int sum = curr + nums[mid];

            if( sum <= upper) {
                ans = mid;
                l = mid + 1;
            }   
            else {
                u = mid - 1;
            }
        }

        return ans;
    }


    // brute force approach
    public long sol1(int[] nums, int lower, int upper) {
        int n = nums.length;
        long count = 0;

        for(int i = 0; i <= n-2; i++) {
            for(int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                if(sum >= lower && sum <= upper) {
                    count += 1;
                }
            }
        }

        return count;
    }
}