class Solution {
    public long coloredCells(int n) {

        long ans = 1;
        for(int i=1; i<=n-1;i++) {
            ans += 4*i;
        }

        return ans;

        // long gridSize = 2*n-1;
        // long halfGridSize = gridSize / 2 + 1;
        // return gridSize*gridSize - 2*(halfGridSize*halfGridSize - halfGridSize);
    }
}