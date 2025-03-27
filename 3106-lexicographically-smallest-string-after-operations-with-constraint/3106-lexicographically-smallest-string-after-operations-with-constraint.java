class Solution {
    public String getSmallestString(String s, int k) {
        StringBuilder str = new StringBuilder();

        int n = s.length();
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);

            // go right side
            int min1 = (int) (ch - 'a');
            int dist1 = 0;
            for(int j=1; j<=Math.min(26, k); j++) {
                int ind = ( ch - 'a' + j) % 26;
                if(ind < min1) {
                    dist1 = j;
                    min1 = Math.min(min1, ind);
                }
            }

            // go left side
            int dist2 = 0;
            int min2 = (int) (ch - 'a');
            for(int j=1; j<=Math.min(26, k); j++) {
                int ind = (ch - 'a' + 26 - j) % 26;
                if(ind < min2) {
                    dist2 = j;
                    min2 = Math.min(min2, ind);
                }
            }

            
             // Get the resulting characters after rotation
            char char1 = (char) (min1 + 'a');
            char char2 = (char) (min2 + 'a');

            // Compare the lexicographically smaller character
            if (min1 < min2) {
                k -= dist1;
                str.append(char1);
            } else if(min1 > min2) {
                k -= dist2;
                str.append(char2);
            }
            else {
                str.append(char1);
                k -= Math.min(dist1, dist2);
            }
        }

        return str.toString();
    }
}