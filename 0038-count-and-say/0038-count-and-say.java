class Solution {
    public String countAndSay(int n) {
        if(n == 1)
            return "1";

        String res = countAndSay(n-1);
        return RLE(res);
    }

    public String RLE(String str) {
        StringBuilder res = new StringBuilder();

        int n = str.length();
        int i = 0;
        while(i < n) {
            int freq = 0;
            char digit = str.charAt(i);
            while(i < n && str.charAt(i) == digit) {
                freq += 1;
                i += 1;
            }

            res.append(freq);
            res.append(digit);
        }

        return res.toString();
    }


}