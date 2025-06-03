class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length; // rows
        int n = classroom[0].length(); // colums

        // count the total number of L's 
        // and, get coordinates for the starting position
        int sr = -1;
        int sc = -1;
        List<int[]> litters = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                char ch = classroom[i].charAt(j);
                if(ch == 'S') {
                    sr = i;
                    sc = j;
                }
                else if(ch == 'L') {
                    litters.add(new int[] {i, j});
                    map.put(i + " " + j, litters.size() - 1);
                }
            }
        }

        int littersCount = litters.size();
        int[][][][] vis = new int[m][n][(1 << littersCount)][energy + 1];

        int[] delR = new int[] {0, 0, 1, -1};
        int[] delC = new int[] {1, -1, 0, 0};
       

        
        Queue<int[]> q = new LinkedList<>();
        // {sr, sc, litterMask, energy, level}
        q.add(new int[] {sr, sc, 0, energy, 0});
        vis[sr][sc][0][energy] = 1;
      
        while(!q.isEmpty()) {
            int[] front = q.poll();
            int r = front[0];
            int c = front[1];
            int mask = front[2];
            int e = front[3];
            int level = front[4];

            if(mask == (1 << littersCount) - 1 ) 
                return level;

            if(e == 0)
                continue;

            for(int del=0; del<4; del++) {
                int nr = r + delR[del];
                int nc = c + delC[del];

                

                if(!isValid(nr, nc, m, n) || classroom[nr].charAt(nc) == 'X' ) 
                    continue;        

                char ch = classroom[nr].charAt(nc);
                int ne = e - 1;
                if(ch == 'R')          
                    ne = energy;
                
                int newMask = mask;
                if(ch == 'L') {
                    newMask = mask | (1 << map.get(nr + " " + nc));
                }

                if(vis[nr][nc][newMask][ne] == 0) {
                    vis[nr][nc][newMask][ne] = 1;
                    q.add(new int[] {nr, nc, newMask, ne, level + 1});
                }
                
            }
        }

        return -1;
    }

    private boolean isValid(int i, int j, int m, int n) {
        if(i >= 0 && i < m && j>=0 && j<n)
            return true;
        return false;
    }
}