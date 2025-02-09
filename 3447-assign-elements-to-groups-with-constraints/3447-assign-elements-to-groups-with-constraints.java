class Solution {

    

    
    public int[] assignElements(int[] groups, int[] elements) {

        int n = groups.length;
        
        Map<Integer, Integer> eleToInd = new HashMap<>();
        for(int i=0; i<elements.length; i++) {
            if(!eleToInd.containsKey(elements[i])) {
                eleToInd.put(elements[i], i);
            }
        }
        
        int[] ans = new int[n];
        for(int i=0; i<n; i++) {
            int size = groups[i];

            int minInd = elements.length;
            for(int div=1; div*div <= size; div++) {
                if(size % div == 0) {
                    int div2 = size / div;

                    if(eleToInd.containsKey(div)) {
                        minInd = Math.min(minInd, eleToInd.get(div));
                    }

                    if(eleToInd.containsKey(div2)) {
                        minInd = Math.min(minInd, eleToInd.get(div2));
                    }
                }
            }

            if(minInd == elements.length) {
                minInd = -1;
            }

            ans[i] = minInd;
        }

        return ans;
    }
}