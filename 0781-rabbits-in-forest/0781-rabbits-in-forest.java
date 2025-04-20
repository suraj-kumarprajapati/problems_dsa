class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        int res = 0;
        for(Map.Entry<Integer, Integer> it : map.entrySet()) {
            int ans = it.getKey();
            int freq = it.getValue();

            int groupFreq = 1 + ans;
            int groups = (int) Math.ceil ((float) freq / (float)groupFreq);
            if(groups == 0)
                groups = 1;
            res += groups * groupFreq;
        }

        return res;
    }
}