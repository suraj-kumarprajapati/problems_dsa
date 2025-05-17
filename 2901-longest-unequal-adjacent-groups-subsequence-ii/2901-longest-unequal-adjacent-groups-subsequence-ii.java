class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        
        int n = words.length;

        for(int i=0; i<n; i++) {
            ans.add(words[i]);
            int lastInd = i;
            for(int j=i+1; j<n; j++) {
                if(words[lastInd].length() == words[j].length() && groups[j] != groups[lastInd] && isHammingOne(words, j, lastInd)) {   
                    ans.add(words[j]);
                    lastInd = j;
                }
            }

            if(ans.size() > 1 || i == n-1)
                break;
            else
                ans.clear();
        }

        return ans;
    }

    private boolean isHammingOne(String[] words, int i1, int i2) {
        int misMatchCount = 0;
        for(int i=0; i<words[i1].length(); i++) {
            if(words[i1].charAt(i) != words[i2].charAt(i))
                misMatchCount += 1;
        }

        return misMatchCount == 1;
    }
}