class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int[] rowSum = new int[n];
        int[] colSum = new int[m];

        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=0; j<m; j++) {
                sum += grid[i][j];
            }

            rowSum[i] = sum;
        }

        for(int j=0; j<m; j++) {
            int sum = 0;
            for(int i=0; i<n; i++) {
                sum += grid[i][j];
            }

            colSum[j] = sum;
        }


        long ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[i][j] == 1 && rowSum[i] >= 2 && colSum[j] >= 2) {
                    ans += (long) (rowSum[i] - 1) * (colSum[j] - 1);
                }
            }
        }

        return ans;

        

    }
}