class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] res = new int[n];

        int left = -1;
        int right = n;
        int i = 0;
        int j = n-1;
        while(i < n ) {
            
            if(nums[i] < pivot) {
                left += 1;
                res[left] = nums[i];
            }

            if(nums[j] > pivot) {
                right -= 1;
                res[right] = nums[j];
            }

            j -= 1;
            i += 1;
        }

        left += 1;
        right -= 1;
        while(left <= right) {
            res[left] = pivot;
            left += 1;
        }
        

        return res;
    }
}