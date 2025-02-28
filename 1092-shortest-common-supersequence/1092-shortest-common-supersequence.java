class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();


        // Find the lcs of str1 and str2
        int[][] dp = new int[m+1][n+1];

        // fill the dp array
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }


        // generate the lcs subsequence
        int r = m;
        int c = n;
        StringBuilder lcs = new StringBuilder();
        while(r!=0 && c!=0) {
            if(str1.charAt(r-1) == str2.charAt(c-1)) {
                lcs.append(str1.charAt(r-1));
                r -= 1;
                c -= 1;
            }
            else {
                if(dp[r][c-1] >= dp[r-1][c]) {
                    c = c-1;
                }
                else {
                    r = r-1;
                }
            }
        }

        lcs.reverse();

        // remove lcs from (str1 + str2)
        StringBuilder s1 = new StringBuilder(str1 + str2);
        int i = 0;
        int j = 0;
        while(i<s1.length() && j<lcs.length()) {
            if(s1.charAt(i) == lcs.charAt(j)) {
                s1.deleteCharAt(i);
                j += 1;
            }
            else {
                i += 1;
            }
        }


        // remove lcs from (str2 + str1)
        StringBuilder s2 = new StringBuilder(str2 + str1);
        i = 0;
        j = 0;
        while(i<s2.length() && j<lcs.length()) {
            if(s2.charAt(i) == lcs.charAt(j)) {
                s2.deleteCharAt(i);
                j += 1;
            }
            else {
                i += 1;
            }
        }


        // return the shorter subsequence out of s1 and s2
        if(s1.length() < s2.length()) {
            // do the final check whether both strings can be formed using this s1
            i = 0;
            j = 0;
            int stringsFormed = 0;
            while(i < s1.length() && j < str1.length()) {
                if(s1.charAt(i) == str1.charAt(j)) {
                    i += 1;
                    j += 1;
                }
                else {
                    i += 1;
                }
            }

            if(j == m) {
                stringsFormed += 1;
            }

            i = 0;
            j = 0;
            while(i < s1.length() && j < str2.length()) {
                if(s1.charAt(i) == str2.charAt(j)) {
                    i += 1;
                    j += 1;
                }
                else {
                    i += 1;
                }
            }

            if(j == m) {
                stringsFormed += 1;
            }

            if(stringsFormed == 2)
                return s1.toString();
        }
        return s2.toString();
    }
}