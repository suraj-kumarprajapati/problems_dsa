class Solution {
    public int maxDistance(String s, int k) {
        
        int n = s.length();

        char[] hor = new char[] {'W', 'E'};
        char[] ver = new char[] {'N', 'S'} ;


        int maxDist = 0;
        for(char h : hor) {
            for(char v : ver) {
                // track remaining dir changes i can do
                int rem = k;
                // track x coordinate
                int xCor = 0;
                // track y coordinate
                int yCor = 0;

                // traverse each dir in s
                for(int i=0; i<n; i++) {
                    // current direction
                    char ch = s.charAt(i);

                    // if curr dir is W or E means horizontal
                    if(ch == 'W' || ch == 'E') {
                        if(ch != h && rem > 0) {
                            ch = (ch == 'W') ? 'E' : 'W';
                            rem -= 1;
                        }
                    }
                    // if curr dir is N or S means vertical
                    else if(ch == 'N' || ch == 'S') {
                        if(ch != v && rem > 0) {
                            ch = (ch == 'N') ? 'S' : 'N';
                            rem -= 1;
                        }
                    }

                    if(ch == 'W') {
                        xCor -= 1;
                    }
                    else if(ch == 'E') {
                        xCor += 1;
                    }
                    else if(ch == 'N') {
                        yCor += 1;
                    }
                    else {
                        yCor -= 1;
                    }

                    maxDist = Math.max(maxDist, Math.abs(xCor) + Math.abs(yCor));
                }
            }
        }


        return maxDist;        
    }
}