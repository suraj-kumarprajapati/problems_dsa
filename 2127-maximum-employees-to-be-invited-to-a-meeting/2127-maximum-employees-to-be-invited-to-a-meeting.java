class Solution {
    public int maximumInvitations(int[] favorite) {
        // no. of nodes
        int n = favorite.length;

        // maintain indegree info of every node
        int[] indegree = new int[n];
        // maintain the maximum path length/depth attached to a cyclic node
        int[] depth = new int[n];

        // fill the initial values in indegree table and depth table
        for(int i=0; i<n; i++) {
            indegree[favorite[i]] += 1;
            depth[i] = 1;
        }

        // now, make a queue to do bfs traversal using kahn's algorithm to find the max depth/length path attached to a cyclic node
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if(indegree[i] == 0)
                q.add(i);
        }

        // start the bfs traversal for no cyclic nodes and fill the depth table for cyclic nodes
        while(!q.isEmpty()) {
            int node = q.poll();
            int nextNode = favorite[node];
            depth[nextNode] = Math.max(depth[nextNode], depth[node] + 1);

            indegree[nextNode] -= 1;
            if(indegree[nextNode] == 0) {
                q.add(nextNode);
            }
        }


        // now, visit all the cycles
        int maxTwoCycleLength = 0;
        int maxNormalCycleLength = 0;
        
        for(int i=0; i<n; i++) {
            // skip the visited cyclic/non-cyclic nodes
            if(indegree[i] == 0)
                continue;

            int currCycleLength = 0;
            int node = i;
            while(indegree[node] != 0) {
                currCycleLength += 1;
                indegree[node] = 0;
                node = favorite[node];
            }

            // curr cycle length is 2 or more than 2
            if(currCycleLength == 2) {
                // continue attaching the 2 length cycles with their max path component
                maxTwoCycleLength += depth[node] + depth[favorite[node]];
            }
            else {
                // if the cycle length is greater than 2 
                maxNormalCycleLength = Math.max(maxNormalCycleLength, currCycleLength);
            }

        }

        return Math.max(maxNormalCycleLength, maxTwoCycleLength);     
    }

}