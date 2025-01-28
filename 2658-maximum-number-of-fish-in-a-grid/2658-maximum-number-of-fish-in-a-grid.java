class Solution {
    
    public int findMaxFish(int[][] grid) {
        return sol1(grid);
    }

    public int sol1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] vis = new int[m][n];
        int ans = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                
                if(grid[i][j] > 0 && vis[i][j] == 0) {
                    int temp = dfs(i, j, grid, vis);
                    ans = Math.max(ans, temp);
                }

            }
        }

        return ans;
    }

    public int dfs(int r, int c, int[][] grid, int[][] vis) {
        int m = grid.length;
        int n = grid[0].length;

        vis[r][c] = 1;

        int[] delR = new int[] {0, 0, -1, 1};
        int[] delC = new int[] {-1, 1, 0, 0};

        int neiFish = 0;
        for(int dir=0; dir<4; dir++) {
            int nr = r + delR[dir];
            int nc = c + delC[dir];

            if(nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc]>0 && vis[nr][nc]==0) {
                int temp = dfs(nr, nc, grid, vis);
                neiFish += temp;
            }
        }

        return grid[r][c] + neiFish;
    }




}