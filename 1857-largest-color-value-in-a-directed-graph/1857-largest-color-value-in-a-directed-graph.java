class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];
        int[][] colorsCount = new int[n][26];

        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            indegree[v] += 1;
        }

        for(int i=0; i<n; i++) {
            colorsCount[i][colors.charAt(i) - 'a'] = 1;
        }


        Queue<Integer> q = new LinkedList<>();       
        for(int i=0; i<n; i++) {
            if(indegree[i] == 0)
                q.add(i);
        }


        int maxCount = 0;
        int visCount = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            visCount += 1;

            int localMax = 0;
            for(int nei : adj.get(node)) {
                for(int c=0; c<26; c++) {
                    colorsCount[nei][c] = Math.max(colorsCount[nei][c], colorsCount[node][c] + ( (int) (colors.charAt(nei) - 'a') == c  ? 1 : 0));
                    localMax = Math.max(localMax, colorsCount[nei][c]);
                }

                indegree[nei] -= 1;
                if(indegree[nei] == 0) 
                    q.add(nei);

                maxCount = Math.max(maxCount, localMax);
            }

            
        }

        return visCount == n ? maxCount :  -1;
    }
}