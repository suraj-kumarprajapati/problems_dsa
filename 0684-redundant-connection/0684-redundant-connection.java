class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        // return sol1(edges);

        return sol2(edges);
    }




    public int[] sol2(int[][] edges) {
        int n = edges.length;

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }

        // traverse each edge
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int[] vis = new int[n+1];

            // check if u and v are already connected in same componenet (only one component possible according to question) of the graph
            if( isConnected(u, v, adj, vis) ) {
                return new int[] {u, v};
            }

            // if not connected, add this edge in the graph
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // pseudo return
        return new int[] {};
    }

    public boolean isConnected(int src, int dest, List<List<Integer>> adj, int[] vis) {
        vis[src] = 1;

        if(src == dest)
            return true;

        boolean conn = false;
        for(int nei : adj.get(src)) {
            if(vis[nei] == 0) {
                conn = conn || isConnected(nei, dest, adj, vis);
            }
        }

        return conn;
    }



    // the idea is that, you try to remove an edge from the adj list by traversing each edge from right to left of the input and check if you are still able to visit all the nodes, if yes then it is considered to be answer
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

            // remove this edge from the adj list and check if you can visit all the nodes or not
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