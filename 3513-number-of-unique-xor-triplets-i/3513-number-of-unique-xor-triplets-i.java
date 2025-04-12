class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if(n < 3)
            return n;

        int max = findMaxNum(n);
        
        return max + 1;
    }

    public int findMaxNum(int n) {
        
        
        int msb = 0;
        
        int sb = 0;
        while(n != 0) {
            int bit = (n & 1);
            if(bit > 0) {
                msb = sb;
            }
            n = n >> 1;
            sb += 1;
        }

        return (int) Math.pow(2, msb + 1) - 1;
    }
}