class Solution {
    public long coloredCells(int n) {
        long N = (long) n;
        return (long) (1 + (N-1)*(2*4 + (N-2)*4)/2);
    }
}