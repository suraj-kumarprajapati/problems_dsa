class Solution {
    public int[] queryResults(int limit, int[][] queries) {

        int[] colors = new int[limit + 1];
        Map<Integer, Integer> colorToNodes = new HashMap<>();

        int m = queries.length;
        int[] ans = new int[m];
        for(int i=0; i<m; i++) {
            int[] query = queries[i];
            int n = query[0];
            int c = query[1];


            int prevColor = colors[n];

            if(prevColor != 0) {
                colorToNodes.put(prevColor, colorToNodes.get(prevColor) - 1);
                if(colorToNodes.get(prevColor) <= 0) {
                    colorToNodes.remove(prevColor);
                }
            }

            colors[n] = c;
            
            colorToNodes.put(c, colorToNodes.getOrDefault(c, 0) + 1);

            ans[i] = colorToNodes.size();
        }

        return ans;
    }
}