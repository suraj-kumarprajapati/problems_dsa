class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        return sol1(edges, bob, amount);
    }


    // two dfs
    public int sol1(int[][] edges, int bob, int[] amount) {
        int n = amount.length;

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        Arrays.fill(parent, -1);
    

        // find the parent and depth of each node
        dfs1(0, 0, -1, adj, depth, parent);

        // correct the amount for path traced by bob
        int currDep = 0;  // track depth from 0 to bob
        int tempBob = bob;
        int bobDep = depth[tempBob];   // track depth from bob to 0
        while(bobDep >= currDep) {
            if(bobDep > currDep) {
                amount[tempBob] = 0;
            }
            else if(bobDep == currDep) {
                amount[tempBob] = amount[tempBob] / 2;
            }

            tempBob = parent[tempBob];
            bobDep = depth[tempBob];
            currDep += 1;
        }


        // second dfs to find the maximum path sum or maximum net income
        return dfs2(0, -1, adj, amount);
    }

    public int dfs2(int node, int par, List<List<Integer>> adj, int[] amount) {

        int maxIncome = Integer.MIN_VALUE;
        for(int nei : adj.get(node)) {
            if(nei != par) {
                int income = amount[node] + dfs2(nei, node, adj, amount);
                maxIncome = Math.max(maxIncome, income);
            }
        }

        return maxIncome == Integer.MIN_VALUE ? amount[node] : maxIncome;
    }

    public void dfs1(int node, int dep, int par, 
    List<List<Integer>> adj, int[] depth, int[] parent) 
    {
        depth[node] = dep;
        parent[node] = par;

        for(int nei : adj.get(node)) {
            if(nei != par) {
                dfs1(nei, dep + 1, node, adj, depth, parent);
            }
        }
    }
}