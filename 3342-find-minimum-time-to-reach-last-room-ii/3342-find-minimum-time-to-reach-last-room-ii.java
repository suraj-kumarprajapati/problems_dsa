class Solution {

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;

        int[][] dis = new int[n][m];
        for(int[] arr1D : dis) {
            Arrays.fill(arr1D, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        pq.add(new int[] {0, 0, 0, 0});
        dis[0][0] = 0;

        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while(!pq.isEmpty()) {
            int[] front = pq.poll();
            int r = front[0];
            int c = front[1];
            int ct = front[2];
            int move = front[3];

            for(int dir=0; dir<4; dir++) {
                int nr = r + dirs[dir][0];
                int nc = c + dirs[dir][1];

                if(isValid(nr, nc, n, m)) {
                    int newDis = Math.max(ct, moveTime[nr][nc]) + (move == 0 ? 1 : 2);

                    if(newDis < dis[nr][nc]) {
                        dis[nr][nc] = newDis;
                        pq.add(new int[] {nr, nc, newDis, move == 0 ? 1 : 0});
                    }
                }
            }
        }

        return dis[n-1][m-1];
    }

    private boolean isValid(int r, int c, int n, int m) {
        if(r >= 0 && c >= 0 && r < n && c < m)
            return true;
        return false;
    }
}