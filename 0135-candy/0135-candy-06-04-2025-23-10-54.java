class Solution {


    // time -> O(3n) and space -> O(2n)
    public int candy(int[] ratings) {
        int minCandies = 0;
        
        // create an array to hold the anser of cadies for each child
        int n = ratings.length;
        int left[] = new int[n];
        left[0] = 1;
        // int right[] = new int[n];
        // right[n-1] = 1;

        int curr = 1;
        int prev = 1;
        
        for(int i=1;i<n;i++) {
            if(ratings[i] > ratings[i-1])
                left[i] = left[i-1] + 1;
            else
                left[i] = 1;
        }

        minCandies = Math.max(1, left[n-1]);

        for(int i=n-2;i>=0;i--) {
            if(ratings[i] > ratings[i+1]) {
                // right[i] = right[i+1] + 1;
                curr = prev+1; 
            }
            else {
                // right[i] = 1;
                curr = 1;
            }
            prev = curr;
            minCandies += Math.max(left[i], curr);
        }

        // for(int i=0;i<n;i++) {
        //     minCandies += Math.max(left[i], right[i]);
        // }
        return minCandies;
    }
}