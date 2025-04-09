class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int l = 0;
        int r = m-1;

        while(l <= r) {
            int col = l + (r - l) / 2;
            int row = findMaxRow(mat, col);

            int left = col - 1 >= 0 ? mat[row][col - 1] : -1;
            int right = col + 1 < m ? mat[row][col + 1] : -1;

            if(mat[row][col] > left && mat[row][col] > right) {
                return new int[] {row, col};
            }
            else if(right > mat[row][col]) {
                l = col + 1;
            }
            else {
                r = col - 1;
            }
        }

        return new int[] {-1, -1};
    } 

    public int findMaxRow(int[][] mat, int col) {
        int n = mat.length;
    
        int max = -1;
        int ans = -1;
        for(int i=0; i<n; i++) {
            if(mat[i][col] > max) {
                max = mat[i][col];
                ans = i;
            }
        }

        return ans;
    }
}  