class Solution {
    // ind =              0 1 2
    // str =              b a c
    // minCharToRight =   a c z
    public String robotWithString(String s) {
        int n = s.length();
        
        char[] minCharToRight = new char[n];
        minCharToRight[n-1] = 'z';
        for(int i=n-2; i>=0; i--) {
            char ch = s.charAt(i + 1);
            minCharToRight[i] = (char) Math.min(ch, minCharToRight[i+1]);
        }

        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            stack.push(ch);

            while(!stack.isEmpty() && stack.peek() <= minCharToRight[i]) {
                res.append(stack.pop());
            }
        }

        return res.toString();
    }

   
}