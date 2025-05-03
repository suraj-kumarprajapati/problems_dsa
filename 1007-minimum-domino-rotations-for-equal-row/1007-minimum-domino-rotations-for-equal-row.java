class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;

        int[] counts = new int[7];
        for(int i=0; i<n; i++) {
            if(tops[i] == bottoms[i]) {
                counts[tops[i]] += 1;
            }
            else {
                counts[tops[i]] += 1;
                counts[bottoms[i]] += 1;
            }
        }

        int minRotations = Integer.MAX_VALUE;
        for(int tile=1; tile<=6; tile++) {
            if(counts[tile] == n) {
                int topAbsenceCount = 0;
                int bottomAbsenceCount = 0;
                for(int i=0; i<n; i++) {
                    if(tops[i] != tile) 
                        topAbsenceCount += 1;
                    if(bottoms[i] != tile)
                        bottomAbsenceCount += 1;
                }

                minRotations = Math.min(minRotations, Math.min(topAbsenceCount, bottomAbsenceCount));
            }
        }

        return minRotations == Integer.MAX_VALUE ? -1 : minRotations;
    }
}