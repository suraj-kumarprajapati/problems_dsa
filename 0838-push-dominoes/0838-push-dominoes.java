class Solution {
    int n;
    int[] nextL;
    int[] prevR;

    public String pushDominoes(String dominoes) {
        this.n = dominoes.length();
        this.nextL = new int[n];
        this.prevR = new int[n];

        char[] arr = dominoes.toCharArray();

        // find nextL and prevR for each . index
        findNextLeft(dominoes);
        findPrevRight(dominoes);

        for(int i=0; i<n; i++) {
            char ch = arr[i];
            if(ch == 'L' || ch == 'R')
                continue;

            if(nextL[i] == n && prevR[i] == -1)
                continue;

            if(nextL[i] == n) {
                arr[i] = 'R';

            }
            else if(prevR[i] == -1) {
                arr[i] = 'L';
            }
            else {
                int distL = nextL[i] - i ;
                int distR = i - prevR[i] ;

                if(distL > distR) {
                    arr[i] = 'R';
                }
                else if(distL < distR) {
                    arr[i] = 'L';
                }
            }
        }

        return new String(arr);

    }

    public void findPrevRight(String dominoes) {
        int i = n-1;
        Arrays.fill(prevR, -1);
        Stack<Integer> stack = new Stack<>();

        while(i >= 0) {
            if(dominoes.charAt(i) == '.') {
                stack.push(i);
                i -= 1;
                continue;
            }

            while(!stack.isEmpty() ) {
                int dotInd = stack.pop();
                if(dominoes.charAt(i) == 'R')
                    prevR[dotInd] = i;
            }
            i -= 1;
        }
    }

    public void findNextLeft(String dominoes) {
        
        int i = 0;
        Arrays.fill(nextL, n);
        Stack<Integer> stack = new Stack<>();

        while(i < n) {
            if(dominoes.charAt(i) == '.') {
                stack.push(i);
                i += 1;
                continue;
            }

            while(!stack.isEmpty() ) {
                int dotInd = stack.pop();
                if(dominoes.charAt(i) == 'L')
                    nextL[dotInd] = i;
            }
            i += 1;
        }
    }

    
}