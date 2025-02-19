class Solution {

    int[] fact = new int[8];

    public int numTilePossibilities(String tiles) {
        // return sol1(tiles);

        calcFact();

        return sol2(tiles);
    }


    public int sol2(String tiles) {
        int n = tiles.length();

        char[] tilesArr = tiles.toCharArray();
        Arrays.sort(tilesArr);
        
        Set<String> seq = new HashSet<>();

        return genSeq(0, "", tilesArr, seq);
    }

    public int genSeq(int ind, String str, char[] tilesArr, Set<String> seq) {
        int n = tilesArr.length;

        // base case
        if(ind == n) {
            if(str.equals("") || seq.contains(str)) {
                return 0;
            }
            else {
                seq.add(str);
                int perm = getPermutation(str);
                return perm;
            }
        }

        // two options -> take this char or don't take it
        // 1. don't take
        int res1 = genSeq(ind+1, str, tilesArr, seq);
        // 2. take 
        int res2 = genSeq(ind+1, str + tilesArr[ind], tilesArr, seq);
        return res1 + res2;
    }

    public void calcFact() {
        int n = fact.length;
        fact[0] = 1;
        fact[1] = 1;

        for(int i=2; i<n; i++) {
            fact[i] = i * fact[i-1];
        }
    }


    public int getPermutation(String str) {
        int n = str.length();
        int perm = fact[n];
        Map<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i<n; i++) {
            char ch = str.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        for(Map.Entry<Character, Integer> it : freq.entrySet()) {
            int f = it.getValue();
            perm = perm / fact[f];
        }

        return perm;
    }

    



    // time -> O(n! * n) : n! for different sequence generation and n for string concatenation
    // space -> O(n! * n) , n! -> no. of sequences, n -> length of each sequence
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