class Solution {
    int[][] dp;

    public int minCost(int[] nums) {
        dp = new int[1001][1001];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return solve(nums, 1, 0);
    }

    public int solve(int[] nums, int curr, int last) {
        int n = nums.length;

        // base case
        if(curr + 1 >= n)
            return Math.max(nums[last], curr < n ? nums[curr] : 0);

        if(dp[curr][last] != -1)
            return dp[curr][last];

        // 3 possibilities
        // first -> ignore last and remove curr and curr+1 indexed element
        int res1 = Math.max(nums[curr], nums[curr+1]) + solve(nums, curr+2, last);

        // second -> ignore the curr and remove last and curr+1 indexed element
        int res2 = Math.max(nums[last], nums[curr+1]) + solve(nums, curr+2, curr);

        // third -> ignore the curr+1 and remove last and curr indexed element
        int res3 = Math.max(nums[last], nums[curr]) + solve(nums, curr+2, curr+1);

        return dp[curr][last] = Math.min(res1, Math.min(res2, res3));
    }
}