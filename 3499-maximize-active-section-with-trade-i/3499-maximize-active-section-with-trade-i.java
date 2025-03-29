class Solution {
    public int maxActiveSectionsAfterTrade(String s) {

        s = '1' + s + '1';

        int n = s.length();


        int ans = 0;
        for(int i=1; i<n-1; i++) {
            if(s.charAt(i) == '1')
                ans += 1;
        }

        int[] prefOnes = new int[n];
        prefOnes[0] = 0;
        for(int i=1; i<n-1; i++) {
            prefOnes[i] = prefOnes[i-1] + ( s.charAt(i) == '1' ? 1 : 0 );
        }

        if(n >= 2) {
            prefOnes[n-1] = prefOnes[n-2];
        }

        int[] suffOnes = new int[n];
        suffOnes[n-1] =  0;
        for(int i=n-2; i>=1; i--) {
            suffOnes[i] = suffOnes[i+1] + ( s.charAt(i) == '1' ? 1 : 0 );
        }

        if(n >= 2) {
            suffOnes[0] = suffOnes[1];
        }

        


        // to store the block of zeroes 
        // previous block
        int[] cb = new int[] {-1, -1};
        // current block
        int[] pb = new int[] {-1, -1};
        for(int i=0; i<n-1; i++) {
            if(s.charAt(i) != s.charAt(i+1)) {
                // on moving from 1 to 0
                if(s.charAt(i) == '1') {
                    // current block start
                    cb[0] = i+1;
                }
                // on moving from 0 to 1
                else {
                    // current block end
                    cb[1] = i;

                    // if pb exists then
                    if(pb[0] != -1) {
                        int pref = pb[0] - 1 >= 0 ? prefOnes[pb[0] - 1] : 0;
                        int suff = cb[1] + 1 < n ? suffOnes[cb[1] + 1] : 0; 
                        ans = Math.max(ans, cb[1] - pb[0] + 1 + pref + suff);
                    }

                    pb = cb.clone();
                }
            }
        }

        return ans;    
    }
}