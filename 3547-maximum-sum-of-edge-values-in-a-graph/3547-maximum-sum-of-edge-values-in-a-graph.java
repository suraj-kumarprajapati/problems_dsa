
    // looking at the problem conditions -> only cycles and chains are possible in a component
    // to maximise the score we have to assign bigger values to the cycles and then smaller values to the chains
    // assign values greedily i.e. bigger values to the bigger cycles and then smaller cycles
    // same applies to the chains


    // algorithm for this problem
    // first using dfs, find components with their sizes
    // also find out whether these components are chains or cycles
    // store the sizes of cyclic components in cycles and sort it in descending order
    // store the sizes of chain components in chains and sort it in descending order
    // calculate the answer greedily



    class Solution {
        private List<List<Integer>> adj;
        private int[] vis;

        public long maxScore(int n, int[][] edges) {
            // make graph (or adj)
            this.adj = new ArrayList<>();
            this.vis = new int[n];
            
            for(int i=0; i<n; i++) {
                adj.add(new ArrayList<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            // find out the chain and cyclic components with their sizes
            List<Integer> cycles = new ArrayList<>();
            List<Integer> chains = new ArrayList<>();

            // use dfs for each component
            for(int i=0; i<n; i++) {
                int[] size = new int[1];
                if(vis[i] == 0) {
                    // if this component is a cycle 
                    if(dfs(i, -1, size)) {
                        cycles.add(size[0]);
                    }
                    else {
                        chains.add(size[0]);
                    }
                }
            }

            // sort the cycles and chains in ascending order
            Collections.sort(cycles, (a, b) -> b-a);
            Collections.sort(chains, (a, b) -> b-a);

            long ans = 0;
            long N = n;

            // calculation for cycles
            for(int x : cycles) {
                ans += N * ((N-1) + (N-2)) * 1l;
                N -= 3;
                x -= 3;

                while(x > 0) {
                    ans += N*(N+2)*1l;
                    x -= 1;
                    N -= 1;
                }
                ans += (N+1)*(N+2)*1l;
            }

            // calculation for chains
            for(int x : chains) {
                if(x == 1)
                    break;
                else if(x == 2) {
                    ans += N*(N-1)*1l;
                    N -= 2;
                    x -= 2;
                }
                else {
                    ans += N*((N-1) + (N-2))*1l;
                    N -= 3;
                    x -= 3;

                    while(x > 0) {
                        ans += N*(N+2)*1l;
                        x -= 1;
                        N -= 1;
                    }
                }
            }

            return ans;
        }

        public boolean dfs(int node, int parent, int[] size) {
            vis[node] = 1;
            size[0] += 1;

            boolean isCycle = false;
            for(int nei : adj.get(node)) {
                if(nei == parent)
                    continue;
            
                if(vis[nei] == 0) {
                    isCycle = isCycle || dfs(nei, node, size);
                }
                else {
                    isCycle = true;
                }
            }

            return isCycle;
        }
    }