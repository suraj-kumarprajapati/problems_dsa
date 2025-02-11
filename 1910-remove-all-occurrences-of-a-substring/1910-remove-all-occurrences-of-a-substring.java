class Solution {
    public String removeOccurrences(String s, String part) {
        return removeOccurrencesBF(s, part);

        // return removeOccurencesUsingStack(s, part);
    }

    public String removeOccurrencesBF(String s, String part) {
        
        while(s.contains(part)) {
            int ind = s.indexOf(part);

            s = s.substring(0, ind) + s.substring(ind + part.length());
        }

        return s;
    }

    public String removeOccurencesUsingStack(String s, String part) {
        int n = s.length();

        Stack<Character> st = new Stack<>();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);

            st.push(c);

            if( st.size() >= part.length() && doesMatch(st, part) ) {
                int j = 0;
                while(j < part.length()) {
                    st.pop();
                    j += 1;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }

        sb.reverse();

        return sb.toString();
    }

    public boolean doesMatch(Stack<Character> st, String part) {
        int m = part.length();
        Stack<Character> temp = new Stack<>();
        temp.addAll(st);

        for(int i=m-1; i>=0; i--) {
            if(!temp.isEmpty() && temp.peek() != part.charAt(i)) {
                return false;
            }
            temp.pop();
        }

        return true;
    }
}