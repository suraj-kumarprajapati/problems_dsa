class Solution {
    public String lexicographicallySmallestString(String s) {
        int n = s.length();


        // first find if s[i...j] is deletable or not
        int[][] deletable = new int[n][n];
        
        // check if consecutive character are deltable or not
        for(int i=0; i<n-1; i++) {
            if(isDeletable(s.charAt(i), s.charAt(i+1))) 
                deletable[i][i+1] = 1;
        }

        // fill remaining places
        // s[i...j] is deletable if s[i+1.....j-1] is deletable
        // or, s[i...j] is deletable when s[i...k] && s[k+1....j] is delatable
        for(int len = 3; len <= n; len++) {
            for(int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                // case 1 
                if(deletable[i+1][j-1] == 1 && isDeletable(s.charAt(i), s.charAt(j))) {
                    deletable[i][j] = 1;
                    continue;
                }

                // case 2 
                for(int k = i + 1; k < j; k++) {
                    if(deletable[i][k] == 1 && deletable[k+1][j] == 1) 
                        deletable[i][j] = 1;
                }
            }
        }



        // dp[i] = lexicographically smallest string in the range [i...n-1]
        String[] dp = new String[n+1];
        dp[n] = "";
        dp[n-1] = s.substring(n-1);

        for(int i = n-2; i >= 0; i--) {
            // case 1 : don't delete the current character
            String str1 = s.charAt(i) + dp[i+1];

            String str2 = s.substring(i);
            // case 2 : delete current character
            for(int j = i + 1; j < n; j++) {
                if(deletable[i][j] == 1) {
                    String temp = dp[j+1];
                    if(str2.compareTo(temp) > 0) {
                        str2 = temp;
                    }
                }
            }

            // compare str1 and str2
            if(str1.compareTo(str2) < 0) {
                dp[i] = str1;
            }
            else {
                dp[i] = str2;
            }
        }

        return dp[0];
    }

    private boolean isDeletable(char ch1, char ch2) {
        int diff = Math.abs((int) (ch1 - ch2));
        return (diff == 1) || (diff == 25);
    }
}