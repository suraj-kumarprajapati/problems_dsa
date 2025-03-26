class Solution {
    public boolean judgeSquareSum(int c) {

       

        for(int i=0; i<=(int)Math.sqrt(c); i++) {
            int sqr1 = i*i;
            int num2 = (int)Math.sqrt(c - sqr1);

            if(num2*num2 == c - sqr1)
                return true;
        }

        return false;
    }
}