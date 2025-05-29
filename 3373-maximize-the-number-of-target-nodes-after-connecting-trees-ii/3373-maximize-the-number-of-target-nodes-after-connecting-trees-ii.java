class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
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

        int[] depths1 = new int[n];
        int[] even1 = new int[n];
        int[] counts1 = new int[2];
        dfs(0, -1, adj1, 0, counts1, depths1);

        for(int i=0; i<n; i++) {
            if(depths1[i] %2 == 0) {
                even1[i] = counts1[0];
            }
            else {
                even1[i] = counts1[1];
            }
        }

        int[] depths2 = new int[m];
        int[] odd2 = new int[m];
        int[] counts2 = new int[2];
        dfs(0, -1, adj2, 0, counts2, depths2);

        int maxOdd = 0;
        for(int i=0; i<m; i++) {
            if(depths2[i] %2 == 0) {
                odd2[i] = counts2[1];
            }
            else {
                odd2[i] = counts2[0];
            }

            maxOdd = Math.max(maxOdd, odd2[i]);
        }

        int[] answer = new int[n];

        for(int i=0; i<n; i++) {
            answer[i] = even1[i] + maxOdd;
        }

        return answer;
    }

    private void dfs(int node, int parent, List<List<Integer>> adj, int depth, int[] counts, int[] depths) {
        counts[depth % 2] += 1;
        depths[node] = depth;

        for(int nei : adj.get(node)) {
            if(nei != parent) {
                dfs(nei, node, adj, depth + 1, counts, depths);
            }
        }
    }
}