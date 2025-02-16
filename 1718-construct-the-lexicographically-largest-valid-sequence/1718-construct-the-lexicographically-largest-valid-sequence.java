class Solution {
    public int[] constructDistancedSequence(int n) {

        int[] seq = new int[2*n - 1];
        int[] placed = new int[n+1];
        placed[0] = 0;
        placed[1] = 1;
        for(int i=2; i<=n; i++) {
            placed[i] = 2;
        }

        genSeq(seq, placed, 0, n);

        return seq;
    }

    public boolean genSeq(int[] seq, int[] placed, int ind, int n) {
        // base case
        if(ind == seq.length)
            return true;

        
        // if this ind is already filled
        if(seq[ind] != 0) {
            return genSeq(seq, placed, ind+1, n);
        }
            


        for(int i = n; i>=1; i--) {
            int nextInd = i == 1 ? ind : ind + i;

            if(placed[i] == 0 ||  nextInd >= seq.length || seq[nextInd] != 0)
                continue;

            seq[ind] = seq[nextInd] = i;
            placed[i] = 0;

            if(genSeq(seq, placed, ind+1, n))
                return true;

            seq[ind] = seq[nextInd] = 0;
            placed[i] = i == 1 ? 1 : 2;
        }

        return false;
    }



}