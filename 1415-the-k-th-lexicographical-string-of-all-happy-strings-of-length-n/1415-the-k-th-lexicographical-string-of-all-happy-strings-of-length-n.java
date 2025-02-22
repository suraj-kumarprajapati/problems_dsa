class Solution {
    char[] set;
    int n;
    int k;
    int count = 0;
    String res;

    public String getHappyString(int n, int k) {
        this.n = n;
        this.k = k;
        this.set = new char[] {'a', 'b', 'c'};
        this.res = "";

        generateHappyStrings(-1, new StringBuilder(), 1);

        return res;
    }

    public void generateHappyStrings(int ind, StringBuilder str, int len) {
        // base case
        if(len > n) {
            count += 1;
            if(count == k)
                res = str.toString();;
            return;
        }

        for(int i=0; i<set.length; i++) {
            if(i != ind) {
                // append this char
                char ch = set[i];
                str.append(ch);
                // call the recursive function
                generateHappyStrings(i, str, len + 1);
                // backtrack
                str.deleteCharAt(str.length() - 1);
            }
        }

    }

}