class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        
        Set<Integer> ind = new HashSet<>();
        for(int i=0; i<n; i++) {
            if(s1.charAt(i) != s2.charAt(i))
                ind.add(i);
        }

        if(ind.size() != 0 && ind.size() != 2)
            return false;
        else if(ind.size() == 0)
            return true;
        else {
            char[] i1 = new char[2];
            char[] i2 = new char[2];

            int j = 0;
            for(int k : ind) {
                i1[j] = s1.charAt(k);
                i2[j] = s2.charAt(k);
                j += 1;
            }

            if(i1[0] == i2[1] && i1[1] == i2[0]) 
                return true;
            else
                return false;
        }

    }
}