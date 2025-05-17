class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int[] freq = new int[3];

        for(int num : nums) {
            freq[num] += 1;
        }

        int curr = 0;
        for(int i=0; i<n; i++) {
            while(freq[curr] == 0) 
                curr += 1;

            nums[i] = curr;
            freq[curr] -= 1;
        }
    }
}