class Solution {
    int[] vals;
    List<List<Integer>> adj;
    int ans;
    int mod = (int) 1e9 + 7;


    public int goodSubtreeSum(int[] vals, int[] par) {
        this.vals = vals;
        int n = vals.length;
        this.ans = 0;
        this.adj = new ArrayList<>();

        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<n; i++) {
            if(par[i] == -1) {
                continue;
            }
            adj.get(par[i]).add(i);
        }

        dfs(0);

        return ans;
    }

    public List<Integer> dfs(int node) {

        List<Integer> subtree = new ArrayList<>();
        subtree.add(node);

        for(int nei : adj.get(node)) {
            List<Integer> sub = dfs(nei);
            for(int it : sub) {
                subtree.add(it);
            }
        }

        ans = (ans +  findAns(subtree)) % mod;
        return subtree;
    }

    public int findAns(List<Integer> subtree) {
        int n = subtree.size();

        int[][] dp = new int[n+1][1 << 10];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int val = fun(0, 0, n, subtree, dp);
        return val;
    }

    // dp[ind][mask] = max sum of good subset of nodes that belong to this subtree rooted at node subtree[ind] including this node itself
    public int fun(int ind, int mask, int n, List<Integer> subtree, int[][] dp) {
        // base case
        if(ind == n)
            return 0;


        if(dp[ind][mask] != -1)
            return dp[ind][mask];

        // get the mask of digits of curr node value
        int currMask = getMask(vals[subtree.get(ind)]);

        int notTake = fun(ind + 1, mask, n, subtree, dp);
        int take = 0;
        if(currMask != -1 && (currMask & mask) == 0 )  {
            take = fun(ind + 1, (mask | currMask), n, subtree, dp) + vals[subtree.get(ind)];
        }

        return dp[ind][mask] = Math.max(take, notTake);        
    }

    // returns the digit mask
    public int getMask(int x) {
        int mask = 0;

        while( x > 0) {
            int dig = x % 10;
            x = x / 10;
            // check if a digit is appearing twice
            if((mask & (1 << dig)) != 0)
                return -1;
            mask = mask | (1 << dig);
        }

        return mask;
    }
}