class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        int n = nums.length;
        int m = l.length;

        for(int i=0; i<m; i++) {
            int left = l[i];
            int right = r[i];


            // Extract and sort the subarray
            int[] subArray = Arrays.copyOfRange(nums, left, right + 1);
            Arrays.sort(subArray);

            // Check for arithmetic sequence
            boolean isArithmetic = true;
            int diff = subArray[1] - subArray[0];

            for (int j = 2; j < subArray.length; j++) {
                if (subArray[j] - subArray[j - 1] != diff) {
                    isArithmetic = false;
                    break;
                }
            }

            ans.add(isArithmetic);
        }

        return ans;
    }
}