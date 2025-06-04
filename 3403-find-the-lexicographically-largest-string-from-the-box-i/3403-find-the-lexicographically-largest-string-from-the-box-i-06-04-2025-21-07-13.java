class Solution {
    public String answerString(String word, int numFriends) {
        int n = word.length();

        if(numFriends == 1)
            return word;

        int maxSplitLength = n - (numFriends - 1);
        String res = "";
        for(int i=0; i<n; i++) {
            String str = word.substring(i, Math.min(i +  maxSplitLength, n));

            int remLength = n - str.length();
            int remFriends = numFriends - 1;

            if(str.compareTo(res) > 0) {
                res = str;
            }
        }

        return res;
    }
}