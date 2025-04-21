class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
         int minCumm = 0;
        int maxCumm = 0;
        int cumm = 0;
        for(int diff : differences) {
            cumm += diff;
            minCumm = Math.min(minCumm, cumm);
            maxCumm = Math.max(maxCumm, cumm);
        }

        int minStartPoint = lower - minCumm;
        int maxStartPoint = upper - maxCumm;

        return minStartPoint < maxStartPoint ? maxStartPoint - minStartPoint + 1 : 0;
    }
}