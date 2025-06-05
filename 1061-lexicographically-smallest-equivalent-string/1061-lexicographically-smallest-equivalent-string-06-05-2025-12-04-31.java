class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i<26; i++) {
            adj.add(new ArrayList<>());
        }

        int n = s1.length();
        for(int i=0; i<n; i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            adj.get(ch1-'a').add(ch2-'a');
            adj.get(ch2-'a').add(ch1-'a');
        }

       

        int[] smallestCharacter = new int[26]; 
        for(int i=0; i<26; i++) {
            int[] vis = new int[26];
            smallestCharacter[i] = dfs(i, adj, vis);
        }

        int m = baseStr.length();
        char[] ans = new char[m];
        for(int i=0; i<m; i++) {
            char ch = baseStr.charAt(i);
            ans[i] = (char) ('a' + smallestCharacter[ch-'a']);
        }

        return new String(ans);       
    }

    private int dfs(int node, List<List<Integer>> adj, int[] vis) {
        vis[node] = 1;

        int smallestNode = node;
        for(int child : adj.get(node)) {
            if(vis[child] == 0) {
                int temp = dfs(child, adj, vis);
                if(temp < smallestNode) {
                    smallestNode = temp;
                }
            }
        }

        return smallestNode;
    }
}