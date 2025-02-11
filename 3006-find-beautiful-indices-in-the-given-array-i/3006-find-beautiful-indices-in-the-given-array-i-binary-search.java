class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans = new ArrayList<>();

        List<Integer> v1 = new ArrayList<>();
        List<Integer> v2 = new ArrayList<>();

        getPatternMatchingIndices(s, a, v1);
        getPatternMatchingIndices(s, b, v2);


        /*
        
        // linear search

        for(int i=0; i<v1.size(); i++) {
            for(int j=0; j<v2.size(); j++) {
                if(Math.abs(v1.get(i) - v2.get(j)) <= k 
                && (ans.isEmpty() || (!ans.isEmpty() && ans.get(ans.size() -1) != v1.get(i)))) 
                    ans.add(v1.get(i));
            }
        }

        Collections.sort(ans);

        */


        // binary search

        for(int i=0; i<v1.size(); i++) {
            int ind1 = v1.get(i);
            int left = ind1 - k;
            int right = ind1 + k;

            int l = 0;
            int r = v2.size() - 1;
            boolean found = false;
            while(l <= r) {
                int mid = l + (r-l)/2;
                int ind2 = v2.get(mid);

                if(ind2 >= left && ind2 <= right) {
                    found = true;
                    break;
                }
                else if(ind2 < left) {
                    l = mid + 1;
                }
                else {
                    r = mid - 1;
                }
            }

            if(found && (ans.isEmpty() 
            || (!ans.isEmpty() && ans.get(ans.size() -1) != ind1))) {
                ans.add(ind1);
            }
        }


        Collections.sort(ans);
        return ans;
    }

    public void getPatternMatchingIndices(String text, String pat, List<Integer> v) {
        String newText = pat + '#' + text;

        int n = newText.length();
        int[] lps = new int[n];
        lps[0] = 0;

        for(int i=1; i<n; i++) {
            int prev_ind = lps[i-1];

            while(prev_ind > 0 && newText.charAt(prev_ind) != newText.charAt(i)) {
                prev_ind = lps[prev_ind - 1];
            }

            if(newText.charAt(prev_ind) == newText.charAt(i)) {
                lps[i] = prev_ind + 1;
            }
            else {
                lps[i] = 0;
            }
        }

        int m = pat.length();
        for(int i=m; i<n; i++) {
            if(lps[i] == m) {
                v.add(i - 2*m);
            }
        }
    }
}