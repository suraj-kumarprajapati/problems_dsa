class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int n = differences.length;

        int minCumm = 0;
        int maxCumm = 0;
        int cumm = 0;
        for(int diff : differences) {
            cumm += diff;
            minCumm = Math.min(minCumm, cumm);
            maxCumm = Math.max(maxCumm, cumm);

            if(maxCumm - minCumm > upper - lower)
                return 0;
        }

        return  upper - (maxCumm - minCumm) - lower + 1;
    }
}