class Solution {
    public String clearDigits(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();

        for(int i=0; i<n; i++) {
            char c = s.charAt(i);

            if( c >= '0' && c <= '9' && !st.isEmpty() ) {
                st.pop();
            }
            else {
                st.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }

        sb.reverse();

        return sb.toString();
    }
}