class Solution {
    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        Arrays.sort(nums);

        // max diff can range from 0 to (max - min) of array
        // minimize the max diff using binary search
        int l = 0;
        int r = nums[n-1] - nums[0];
        int ans = r;
        while(l <= r) {
            int mid = l + (r-l)/2;

            // count unique pairs such that absolute diff of such pairs <= mid
            int count = countPairs(nums, mid);
            if(count >= p) {
                ans = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }

        return ans;
    }

    public int countPairs(int[] arr, int maxDiff) {
        int n = arr.length;

        int i = 0;
        int count = 0; 
        while(i+1 < n) {
            int diff = Math.abs(arr[i] - arr[i+1]);
            if(diff <= maxDiff) {
                count += 1;
                i += 2;
            }
            else {
                i += 1;
            }
        }

        return count;
    }
}