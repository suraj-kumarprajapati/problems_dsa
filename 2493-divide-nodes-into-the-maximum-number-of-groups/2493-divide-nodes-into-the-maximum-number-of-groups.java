class Solution {
    public int magnificentSets(int n, int[][] edges) {
        return sol1(n, edges);
    }


    public int sol1(int n, int[][] edges) {

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

        
        int ans = -1;
        for(int i=1; i<=n; i++) {
            
            int maxGroup = -1;
           
            int[] vis = new int[n+1];
            Arrays.fill(vis, -1);
            if(!isBipartite(i, adj, vis, 0)) {
                maxGroup = -1;
                continue;
            }

            Arrays.fill(vis, -1);
            int[] groups = new int[n+1];
            maxGroup = findGroupsCount(i, adj, vis, groups);
            for(int j=0; j<n; j++) {
                if(vis[j] == 0) {
                    maxGroup += findGroupsCount(j, adj, vis, groups);
                }
            }
            
            ans = Math.max(ans, maxGroup);
        }

        return ans;
    }

    public int findGroupsCount(int st, List<List<Integer>> adj, int[] vis, int[] groups) {
            Queue<Integer> q = new LinkedList<>();
            q.add(st);
            vis[st] = 1;
            groups[st] = 1;
            int maxGroup = 1;
            while(!q.isEmpty()) {
                int node = q.poll();

                for(int nei : adj.get(node)) {
                    if(vis[nei] == -1)  {
                        q.add(nei);
                        vis[nei] = 1;
                        groups[nei] = groups[node] + 1;
                        maxGroup = groups[node] + 1;
                    }
                }
            }

            return maxGroup;

    }

    public boolean isBipartite(int node, List<List<Integer>> adj, int[] vis, int curr) {
        vis[node] = curr;

        for(int nei : adj.get(node)) {
            if(vis[nei] == -1) {
                if( !isBipartite(nei, adj, vis, (~curr)) ) {
                    return false;
                }
            }
            else if(vis[nei] == curr) {
                return false;
            }
        }

        return true;
    }
}