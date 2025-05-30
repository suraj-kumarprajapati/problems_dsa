class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<n; i++) {
            if(edges[i] == -1)
                continue;
            adj.get(i).add(edges[i]);
        }

        int[] minDist1 = new int[n];
        Arrays.fill(minDist1, Integer.MAX_VALUE);
        minDist1[node1] = 0;
        int[] minDist2 = new int[n];
        Arrays.fill(minDist2, Integer.MAX_VALUE);
        minDist2[node2] = 0;

        findMinDist(adj, node1, minDist1, n);
        findMinDist(adj, node2, minDist2, n);

        int dis = Integer.MAX_VALUE;
        int ind = -1;
        for(int i=0; i<n; i++) {
            if(minDist1[i] == Integer.MAX_VALUE || minDist2[i] == Integer.MAX_VALUE)
                continue;
            if ( Math.max(minDist1[i], minDist2[i]) < dis) {
                dis = Math.max(minDist1[i], minDist2[i]);
                ind = i;
            }
        }

        return dis == Integer.MAX_VALUE ? -1 : ind;
    }

    private void findMinDist(List<List<Integer>> adj, int src, int[] minDist, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {src, 0});

        while(!pq.isEmpty()) {
            int[] front = pq.poll();
            int node = front[0];
            int dist = front[1];

            for(int nei : adj.get(node)) {
                int neiNewDist = dist + 1;
                if(neiNewDist < minDist[nei]) {
                    minDist[nei] = neiNewDist;
                    pq.add(new int[] {nei, neiNewDist});
                }
            }
        }
    }
}