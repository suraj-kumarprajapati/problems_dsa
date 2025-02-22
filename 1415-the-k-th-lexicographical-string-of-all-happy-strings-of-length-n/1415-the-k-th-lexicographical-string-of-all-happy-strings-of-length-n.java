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

        return generateHappyStrings(-1, new StringBuilder(), 1);

    }

    public String generateHappyStrings(int ind, StringBuilder str, int len) {
        // base case
        if(len > n) {
            count += 1;
            if(count == k)
                return str.toString();;
            return "";
        }

        for(int i=0; i<set.length; i++) {
            if(i != ind) {
                // append this char
                char ch = set[i];
                str.append(ch);
                // call the recursive function
                String s = generateHappyStrings(i, str, len + 1);
                if(!s.equals(""))
                    return s;
                // backtrack
                str.deleteCharAt(str.length() - 1);
            }
        }

        return "";
    }

}