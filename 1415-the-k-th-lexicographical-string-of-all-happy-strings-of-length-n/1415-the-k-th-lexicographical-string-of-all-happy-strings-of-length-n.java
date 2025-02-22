class Solution {
    char[] set;
    int n;
    int k;
    int count = 0;



    public String getHappyString(int n, int k) {
        // return sol1(n, k);

        return sol2(n, k);
    }
    
    // using cominatorics
    public String sol2(int n, int k) {

        int total = 3 * (1 << (n-1));
        if(k > total)
            return "";
        
        char[] s = new char[n];

        int startA = 1;
        int startB = (1<<(n-1)) + 1;
        int startC = 2*(1<<(n-1)) + 1;

        Map<Character, Character> nextSmallest = new HashMap<>();
        Map<Character, Character> nextGreatest = new HashMap<>();
        nextSmallest.put('a', 'b');
        nextSmallest.put('b', 'a');
        nextSmallest.put('c', 'a');

        nextGreatest.put('a', 'c');
        nextGreatest.put('b', 'c');
        nextGreatest.put('c', 'b');


        if(k < startB) {
            s[0] = 'a';
            k = k - startA;
        }
        else if(k < startC) {
            s[0] = 'b';
            k = k - startB;
        }
        else {
            s[0] = 'c';
            k = k - startC;
        }


        for(int i=1; i<n; i++) {
            int mid = (1 <<(n-i-1));

            if(k < mid) {
                s[i] = nextSmallest.get(s[i-1]);
            }
            else {
                s[i] = nextGreatest.get(s[i-1]);
                k = k-mid;
            }

            
        }

        return new String(s);
    }


    // using recursion + backtracking
    public String sol1(int n, int k) {
        this.n = n;
        this.k = k;
        this.set = new char[] {'a', 'b', 'c'};

        return generateHappyStrings(-1, new StringBuilder(), 1);
    }

    public String generateHappyStrings(int ind, StringBuilder str, int len) {
        // base case
        if(len > n) {
            count += 1;
            if(count == k)
                return str.toString();;
            return "";
        }

        for(int i=0; i<set.length; i++) {
            if(i != ind) {
                // append this char
                char ch = set[i];
                str.append(ch);
                // call the recursive function
                String s = generateHappyStrings(i, str, len + 1);
                if(!s.equals(""))
                    return s;
                // backtrack
                str.deleteCharAt(str.length() - 1);
            }
        }

        return "";
    }

}