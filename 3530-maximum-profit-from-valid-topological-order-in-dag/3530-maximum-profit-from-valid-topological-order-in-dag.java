class Solution {
    int ord = 1;
    public int maxProfit(int n, int[][] edges, int[] score) {

        List<List<Integer>> adj = new ArrayList<>();
        int[] ind = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            ind[v] += 1;
            adj.get(u).add(v);
        }

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> score[a] - score[b]);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (ind[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            q.add(pq.poll());
        }

        int ans = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            ans = ans + (score[node] * ord);
            ord += 1;

            for (int neiNode : adj.get(node)) {
                ind[neiNode] -= 1;
                if (ind[neiNode] == 0) {
                    pq.add(neiNode);
                }
            }

            while (!pq.isEmpty()) {
                q.add(pq.poll());
            }
        }

        return ans;
    }
}
