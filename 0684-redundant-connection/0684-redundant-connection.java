class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        return sol1(edges);
    }

    public int[] sol1(int[][] edges) {
        int n = edges.length;

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u); 
        }

        int[] ans = new int[] {-1, -1};
        for(int i=n-1; i>=0; i--) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];

            // remove this edge from the adj list and check if cycle exists of not
            adj.get(u).remove(Integer.valueOf(v));
            adj.get(v).remove(Integer.valueOf(u));


            int[] vis = new int[n+1];

            dfs(1, adj, vis);

            int count = 0;
            for(int j=1; j<=n; j++) {
                if(vis[j] == 1)
                    count += 1;
            }

            if(count == n) {
                ans = edge;
                break;
            }
          

            // again attach this edge in the graph
            adj.get(u).add(v);
            adj.get(v).add(u);

        }

        return ans;
    }

    public void dfs(int node, List<List<Integer>> adj, int[] vis) {
        vis[node] = 1;

        for(int nei : adj.get(node)) {
            if(vis[nei] == 0) {
                dfs(nei, adj, vis);
            }
        }
    }
}