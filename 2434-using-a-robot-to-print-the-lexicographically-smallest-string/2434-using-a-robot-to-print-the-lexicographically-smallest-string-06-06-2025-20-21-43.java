class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] count = new int[26];

        for(char c : s.toCharArray()) {
            count[c-'a'] += 1;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        int minChar = 'a';
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            stack.push(ch);
            count[ch-'a'] -= 1;

            while(minChar != 'z' && count[minChar - 'a'] == 0) {
                minChar += 1;
            }

            while(!stack.isEmpty() && stack.peek() <= minChar) {
                ans.append(stack.pop());
            }
        }

        return ans.toString();
    }

   
}