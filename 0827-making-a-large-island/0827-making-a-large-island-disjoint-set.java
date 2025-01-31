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

    public int getSize(int node) {
        return this.size[node];
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
        // return sol1(grid);

        // return sol2(grid);

        return sol3(grid);
    }


    // using disjoint set
    public int sol3(int[][] grid) {
        int n = grid.length;

        // initialize ds
        DisjointSet ds = new DisjointSet(n*n);


        // union adjacent 1's in ds
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) {
                    int currNodeInd = i*n + j;
                    for(int dir=0; dir<4; dir++) {
                        int nr = i + delR[dir];
                        int nc = j + delC[dir];

                        if(nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                            int neiNodeInd = nr*n + nc;
                            ds.unionBySize(currNodeInd, neiNodeInd);
                        }
                    }
                }
            }
        }



        int maxIsland = 0;
        boolean hasZero = false;

        for(int r=0; r<n; r++) {
            for(int c=0; c<n; c++) {
                if(grid[r][c] == 0) {
                    hasZero = true;

                    int currIsland = 1;
                    Set<Integer> set = new HashSet<>();
                    for(int dir=0; dir<4; dir++) {
                        int nr = r + delR[dir];
                        int nc = c + delC[dir];

                        if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc] == 1) {
                            int neiNodeInd = nr*n + nc;
                            int par = ds.findParent(neiNodeInd);
                            set.add(par);
                        }
                    }

                    for(int par : set) {
                        currIsland += ds.getSize(par);
                    }

                    maxIsland = Math.max(maxIsland, currIsland);
                }
            }
        }

        if(!hasZero)
            return n*n;

        return maxIsland;
    }


    // time -> O(n^2)
    public int sol2(int[][] grid) {
        int n = grid.length;
        int max = 0;

        Map<Integer, Integer> map = new HashMap<>();
        int[][] vis = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                vis[i][j] = -1;
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1 && vis[i][j] == -1) {
                    int key = i*n + j;
                    int compSum = bfs(i, j, grid, vis, key);
                    max = Math.max(max, compSum);
                    map.put(key, compSum);
                }
            }
        }


        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 0) {
                    int currCompSum = 1;
                    Set<Integer> set = new HashSet<>();

                    for(int dir=0; dir<4; dir++) {
                        int nrow = i + delR[dir];
                        int ncol = j + delC[dir];
                        

                        if(isValid(nrow, ncol, n) && vis[nrow][ncol] != -1) {
                            int key = vis[nrow][ncol];
                            set.add(key);
                        }
                    }

                    for(int k : set) {
                        currCompSum += map.get(k);
                    }

                    max = Math.max(max, currCompSum);
                }
            }
        }

        return max;

    }

    public int bfs(int r, int c, int[][] grid, int[][] vis, int key) {
        int n = grid.length;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        vis[r][c] = key;
        int compSum = 0;

        while(!q.isEmpty()) {
            int[] nei = q.poll();
            int row = nei[0];
            int col = nei[1];
            compSum += 1;

            for(int dir=0; dir<4; dir++) {
                int nrow = row + delR[dir];
                int ncol = col + delC[dir];

                if(isValid(nrow, ncol, n) && grid[nrow][ncol] == 1 && vis[nrow][ncol] == -1) {
                    q.add(new int[] {nrow, ncol});
                    vis[nrow][ncol] = key;
                }
            }
        }

        return compSum;
    }


    // O(n^4)
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