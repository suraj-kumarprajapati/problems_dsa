class Solution {
    public int punishmentNumber(int n) {
        return sol1(n);
    }


    // using recursion
    public int sol1(int n) {
        int pn = 0;
        for(int i=1; i<=n; i++) {
            int sq = i*i;
            String currString = Integer.toString(sq);
            boolean currRes = canPartition(currString, 0 , i);

            if( currRes ) {
                pn += sq;
            }
        }

        return pn;
    }

    public boolean canPartition(String str, int ind, int targetSum) {
        int m = str.length();

        // base case
        if(ind == m) {
            return (targetSum == 0);
        }


        int sum = 0;
        for(int i = ind; i < m; i++) {
            char ch = str.charAt(i);
            sum = sum*10 + (ch - '0');

            if(sum > targetSum)
                break;
            
            if( canPartition(str, i+1, targetSum - sum) )
                return true;
        }

        return false;
    }
}