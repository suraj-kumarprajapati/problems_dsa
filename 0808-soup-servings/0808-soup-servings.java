class Solution {
    public double soupServings(int n) {
        int m = (int) Math.ceil(n/25.0);

        Map<String, Double> memo = new HashMap<>();
        for(int i=1; i<=m; i++) {
            if(prob(i, i, memo) > (1 - (1e-5)))
                return 1;
        }

        return prob(m, m, new HashMap<String, Double>());
    }

    public double prob(int i, int j, Map<String, Double> memo) {
        if(i <= 0 && j <= 0)
            return 0.50;

        if(i <= 0 )
            return 1;

        if(j <= 0)
            return 0;
        
        String key = i + "," + j;

        if(memo.containsKey(key))
            return memo.get(key);

        double res = (prob(i-4, j, memo) + prob(i-3, j-1, memo) + prob(i-2, j-2, memo) + prob(i-1, j-3, memo)) / 4;
        memo.put(key, res);
        return res;
    }
}