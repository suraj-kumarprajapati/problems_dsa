class Solution {
    public boolean judgeSquareSum(int c) {
        // return sol1(c);

        return sol2(c);
        
    }

    // time -> O(sqrt(c) * log(c))
    public boolean sol1(int c) {
        for(int i=0; i<=(int)Math.sqrt(c); i++) {
            int sqr1 = i*i;
            int num2 = (int)Math.sqrt(c - sqr1);

            if(num2*num2 == c - sqr1)
                return true;
        }

        return false;
    }

    public boolean sol2(int c) {
        long b = (long) Math.sqrt(c);
        long a = 0;

        while(a <= b) {
            long res = a*a + b*b ;
            if(res == c)
                return true;
            else if(res < c) {
                a += 1;
            }
            else {
                b -= 1;
            }
        }

        return false;
    }


}