class Solution {
    public long coloredCells(int n) {

        long gridSize = 2*n-1;
        long halfGridSize = gridSize / 2 + 1;
        return gridSize*gridSize - 2*(halfGridSize*halfGridSize - halfGridSize);
    }
}