
class DSU {
    private int N;
    private int[] rank;
    private int[] size;
    private int[] parent;

    public DSU(int N) {
        this.N = N;
        this.size = new int[N+1];
        this.parent = new int[N+1];
        this.rank = new int[N+1];

        for(int node = 0; node <= N; node++) {
            size[node] = 1;
            parent[node] = node;
            rank[node] = 0;
        }
    }

    // find the ultimate parent of the node
    public int findUParent(int node) {
        if(parent[node] == node) {
            return node;
        }

        return parent[node] = findUParent(parent[node]);
    }

    // check if both nodes belong to the same component of not
    public boolean sameComponent(int node1, int node2) {
        int par1 = findUParent(node1);
        int par2 = findUParent(node2);

        return par1 == par2;
    }
    
    // connect these two nodes by rank
    public void unionByRank(int node1, int node2) {
        int par1 = findUParent(node1);
        int par2 = findUParent(node2);

        if(par1 == par2)
            return;

        if(rank[par1] < rank[par2]) {
            parent[par1] = par2;
        }
        else if(rank[par1] > rank[par2]) {
            parent[par2] = par1;
        }
        else {
            parent[par1] = par2;
            rank[par2] += 1;
        }
    }


    // connect these two nodes by size
    public void unionBySize(int node1, int node2) {
        int par1 = findUParent(node1);
        int par2 = findUParent(node2);

        if(par1 == par2)
            return;

        if(size[par1] < size[par2]) {
            parent[par1] = par2;
            size[par2] += size[par1];
        }
        else {
            parent[par2] = par1;
            size[par1] += size[par2];
        }
    }
}








class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        // return sol1(edges);

        // return sol2(edges);

        // return sol3(edges);

        return sol4(edges);
    }


    // using the disjoint set union concept
    public int[] sol4(int[][] edges) {
        int n = edges.length;

        DSU dsu = new DSU(n);
        for(int[] edge : edges) {
            if( dsu.sameComponent(edge[0], edge[1]) ) {
                return edge;
            }
            dsu.unionByRank(edge[0], edge[1]);
        }

        // pseudo return
        return new int[] {};
    }


    // time -> O(n), space -> O(n)
    public int[] sol3(int[][] edges) {
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


        int[] cycleStart = new int[] {-1};
        int[] vis = new int[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        // find the starting node of the cycle and mark all the cyclic nodes by maintaing the parent of each cyclic node
        DFS(1, adj, vis, parent, cycleStart);

        // after finding the starting node of the cycle, add all the cyclic nodes into the hashset or hashmap by traversing the cycle once again
        Set<Integer> set = new HashSet<>();
        int node = cycleStart[0];
        do {
            set.add(node);
            node = parent[node];
        } while(node != cycleStart[0] );


        // traverse each edge from right to left and check if both nodes of this edge is part of the cycle
        // if yes, then this is the requried answer
        for(int i=n-1; i>=0; i--) {
            int[] edge = edges[i];
            int u = edge[0];
            int v = edge[1];

            if(set.contains(u) && set.contains(v)) {
                return new int[] {u, v};
            }
        }

        // pseudo return
        return new int[] {};
    }
    
    // find the starting node and mark all nodes of cycle using DFS
    public void DFS(int node, List<List<Integer>> adj, int[] vis, int[] parent, int[] cycleStart) {
        vis[node] = 1;

        for(int nei : adj.get(node)) {
            if(vis[nei] == 0) {
                parent[nei] = node;
                DFS(nei, adj, vis, parent, cycleStart);
            }
            // if already visited, (since it's undirected graph) check if the
            // eni is parent[node] or not
            // if yes, then we have not found the start of the cycle
            // if no && cycleStart = -1, then it is the start of the cycle
            else if(nei != parent[node] && cycleStart[0] == -1) {
                cycleStart[0] = nei;
                parent[nei] = node;
            }
        }
    }



    // time -> O(n^2), space -> O(n)
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