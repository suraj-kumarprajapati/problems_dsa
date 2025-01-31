class DisjointSet {
    private int[] rank;
    private int[] parent;
    private int[] size;

    public DisjointSet(int n) {
        this.rank = new int[n + 1];
        this.parent = new int[n + 1];
        this.size = new int[n+1];

        for(int i=0;i<=n;i++) {
            this.rank[i] = 0;
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    public int findParent(int u) {
        if(u == parent[u])
            return u;

        return parent[u] = findParent(parent[u]);
    }

    public void disconnect(int u) {
        parent[u] = u;
    }


    public boolean find(int u, int v) {
        return findParent(u) == findParent(v);
    }

    public void unionByRank(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv)
            return;

        if(rank[pu] < rank[pv]) {
            parent[pu] = pv;
        }
        else if(rank[pu] > rank[pv]) {
            parent[pv] = pu;
        }
        else {
            parent[pv] = pu;
            rank[pu] += 1;
        }
    }

    public void unionBySize(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv)
            return;

        if(size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        }
        else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}




class Solution {

    int[] delR = new int[] {0, 0, -1, 1};
    int[] delC = new int[] {-1, 1, 0, 0};

    public int largestIsland(int[][] grid) {
        return sol1(grid);
    }


    public int sol1(int[][] grid) {
        int n = grid.length;

        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int[][] vis = new int[n][n];
                int compCount = dfs(i, j, grid, vis);
                max = Math.max(max, compCount);
            }
        }

        return max;
    }


    public int dfs(int r, int c, int[][] grid, int[][] vis) {
        int n = grid.length;


        vis[r][c] = 1;
        

        int compCount = 1;
        for(int dir=0; dir<4; dir++) {
            int nr = r + delR[dir];
            int nc = c + delC[dir];

            if(isValid(nr, nc, n) && grid[nr][nc] == 1 && vis[nr][nc] == 0) {
                compCount += dfs(nr, nc, grid, vis);
            }
        }

        return compCount;       
    }


    




    

    public boolean isValid(int row, int col, int n) {
        if(row>=0 && row<n && col>=0 && col<n)
            return true;
        return false;
    }
}