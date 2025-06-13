class Solution {
    public int minimizeArrayValue(int[] nums) {
        int n = nums.length;

        int max= Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            max = Math.max(max, nums[i]);
        }
        
        int l = nums[0];
        int r = max;
        int ans = r;
        while(l <= r) {
            int mid = l + (r-l)/2;
            if(isValid(nums, mid)) {
                ans = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }

        return ans;
    }

    // check if the current maxValue is maximum possible integer after performing the operations on this array
    private boolean isValid(int[] nums, long expectedMax) {
        int n = nums.length;
        long[] arr = new long[n];
        for(int i=0; i<n; i++) {
            arr[i] = nums[i];
        }
        
        for(int i=0; i<n-1; i++) {
            if(arr[i] > expectedMax)
                return false;

            long buffer = expectedMax - arr[i];
            arr[i+1] = arr[i+1] - buffer;
        }

        return arr[n-1] <= expectedMax;
    }
}