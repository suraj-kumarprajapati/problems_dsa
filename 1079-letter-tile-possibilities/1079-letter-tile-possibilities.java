class Solution {
    public int numTilePossibilities(String tiles) {
        return sol1(tiles);
    }

    public int sol1(String tiles) {
        int n = tiles.length();
        Set<String> seq = new HashSet<>();
        boolean[] used = new boolean[n];

        generateSequences(tiles, "", seq, used);

        return seq.size();
    }

    public void generateSequences(String tiles, String str, 
    Set<String> seq, boolean[] used) {
        int n = tiles.length();

        if(!str.equals(""))
            seq.add(str);

        for(int i=0; i<n; i++) {
            if(!used[i]) {
                used[i] = true;
                generateSequences(tiles, str + tiles.charAt(i), seq, used);
                used[i] = false;
            }
        }
    }


}