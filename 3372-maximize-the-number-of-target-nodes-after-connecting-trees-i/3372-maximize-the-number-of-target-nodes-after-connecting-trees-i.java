class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<List<Integer>> adj1 = new ArrayList<>();
        List<List<Integer>> adj2 = new ArrayList<>();

        for(int i=0; i<n; i++) {
            adj1.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            adj2.add(new ArrayList<>());
        }

        for(int[] edge : edges1) {
            int u = edge[0];
            int v = edge[1];
            adj1.get(u).add(v);
            adj1.get(v).add(u);
        }

        for(int[] edge : edges2) {
            int u = edge[0];
            int v = edge[1];
            adj2.get(u).add(v);
            adj2.get(v).add(u);
        }

        int[] counts1 = new int[n];
        int[] counts2 = new int[m];

        countReachableNodes(adj1, n, counts1, k);
        countReachableNodes(adj2, m, counts2, k-1);

        int maxNodeCount2 = 0;
        for(int i=0; i<m; i++) {
            maxNodeCount2 = Math.max(maxNodeCount2, counts2[i]);
        }

        int[] answer = new int[n];
        for(int i=0; i<n; i++) {
            answer[i] = counts1[i] + maxNodeCount2;
        }

        return answer;
    }

    private void countReachableNodes(List<List<Integer>> adj, int n, int[] counts, int k) {
        int[] vis = null;
        Queue<int[]> q = null;

        for(int i=0; i<n; i++) {
            q = new LinkedList<>();
            vis = new int[n];
            q.add(new int[] {i, 0});
            vis[i] = 1;

            while(!q.isEmpty()) {
                int[] front = q.poll();
                int node = front[0];
                int dist = front[1];

                if(dist <= k) {
                    counts[i] += 1;

                    for(int nei : adj.get(node)) {
                        if(vis[nei] == 0) {
                            q.add(new int[] {nei, dist + 1});
                            vis[nei] = 1;
                        }
                    }
                }
            }
        }
    }
}