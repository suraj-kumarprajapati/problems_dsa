class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int n = s.length();
        int M = (int) 1e9 + 7;

        int[] freq = new int[26];
        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            freq[ch-'a'] += 1;
        }

        while(t > 0) {
            int[] newFreq = new int[26];
            for(int i=0; i<26; i++) {
                if(freq[i] == 0) {
                    continue;
                }
                else if(i == 25) {
                    newFreq[0] = (newFreq[0] + freq[i]) % M;
                    newFreq[1] = (newFreq[1] + freq[i]) % M;
                }
                else {
                    newFreq[i + 1] = (newFreq[i + 1] + freq[i]) % M;
                }
            }

            for(int i=0; i<26; i++) {
                freq[i] = newFreq[i];
            }

            t -= 1;
        }

        int length = 0;
        for(int i=0; i<26; i++) {
            length = (length + freq[i]) % M;
        }

        return length;
    }
}