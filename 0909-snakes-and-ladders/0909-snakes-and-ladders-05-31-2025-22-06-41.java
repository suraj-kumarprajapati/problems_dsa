class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        return sol(1, board, n);
    }

    // bfs
    public int sol(int start, int[][] board, int n) {
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {start, 0}); 

        int[] vis = new int[n*n + 1];
        vis[1] = 1;
        while(!q.isEmpty()) {
            int[] front = q.poll();
            int curr = front[0];
            int dist = front[1];

            if(curr == n*n)
                return dist;

            for(int i=1; i<=6; i++) {
                int nextNum = Math.min(curr + i, n*n);
                int[] nextCoordinates = getRowCol(nextNum, n);
                int nr = nextCoordinates[0];
                int nc = nextCoordinates[1];
                int next = board[nr][nc] == -1 ? nextNum : board[nr][nc];
                if(vis[next] == 0) {
                    q.add(new int[] {next, dist + 1});
                    vis[next] = 1;
                }
            }
        }

        return -1;
    }

    private int[] getRowCol(int num, int n) {
        int row = n - 1 - (num - 1) / n;
        int col = (num - 1) % n;
        
        // For rows that are counted from right to left (odd-indexed from bottom)
        if (((n - 1 - row) % 2) == 1) {
            col = n - 1 - col;
        }
        
        return new int[] {row, col};
    }

}