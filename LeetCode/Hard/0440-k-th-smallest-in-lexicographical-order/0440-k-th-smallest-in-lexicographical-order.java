class Solution {
    int cnt;

    public int findKthNumber(int n, int k) {
        // return sol1(n, k);

        return sol2(n, k);
    }

    public int sol2(int n, int k) {
        int curr = 1;
        k--;

        while (k > 0) {
            int step = countSteps(n, curr, curr + 1);
            // If the steps are less than or equal to k, we skip this prefix's subtree
            if (step <= k) {
                // Move to the next prefix and decrease k by the number of steps we skip
                curr++;
                k -= step;
            } else {
                // Move to the next level of the tree and decrement k by 1
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

   // To count how many numbers exist between prefix1 and prefix2
    private int countSteps(int n, long prefix1, long prefix2) {
        int steps = 0;
        while (prefix1 <= n) {
            steps += Math.min(n + 1, prefix2) - prefix1;
            prefix1 *= 10;
            prefix2 *= 10;
        }
        return steps;
    }

    // O(n) -> TLE
    public int sol1(int n, int k) {
        this.cnt = k;
        return fun(0, n, k);
    }

    public int fun(int num, int n, int k) {
        if(num > n) 
            return 0;

        if(num != 0)
            cnt = cnt - 1;

        if(cnt == 0)
            return num;

        int start = num == 0 ? 1 : num * 10;
        int end = num == 0 ? 10 : start + 10;
        int ans = 0;
        for(int i=start; i<end; i++) {
            ans = Math.max(ans, fun(i, n, k));
        }

        return ans;
    }
}