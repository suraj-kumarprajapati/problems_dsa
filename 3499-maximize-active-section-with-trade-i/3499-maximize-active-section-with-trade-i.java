class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        // return sol1(s);

        return sol2(s);
    }

    public int sol2(String s) {
        int n = s.length();

        // to store ans
        int ans = 0;
        for(int i=0; i<n; i++) {
            if(s.charAt(i) == '1')
                ans += 1;
        }

        // to store the left portion zeroes
        int z1 = 0;

        // to store the maximum conversions possible in one trade
        int maxDelta = 0;

        int i = 0;
        while(i < n) {
            int ones = 0;
            int z2 = 0;

            while(i < n && s.charAt(i) == '1') {
                ones += 1;
                i += 1;
            }

            while(i < n && s.charAt(i) == '0') {
                z2 += 1;
                i += 1;
            }

            if(z1 >= 1 && ones >= 1 && z2 >= 1) {
                maxDelta = Math.max(maxDelta, z1 + z2);
            }

            z1 = z2;
        }

        return ans + maxDelta;
        
    }

    public int sol1(String s) {
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