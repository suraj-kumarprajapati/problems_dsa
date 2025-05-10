class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0;
        long zeroes1 = 0;

        long sum2 = 0;
        long zeroes2 = 0;

        for(int num : nums1) {
            if(num == 0) zeroes1 += 1;
            else sum1 += (long)num;
        }

        for(int num : nums2) {
            if(num == 0) zeroes2 += 1;
            else sum2 += (long)num;
        }


        if(sum1 + zeroes1 <= sum2 + zeroes2) {
            long diff = sum2 + zeroes2 - sum1;
            if(zeroes1 > diff || (diff != 0 && zeroes1 == 0))
                return -1;
            return sum2 + zeroes2;
        }
        else {
            long diff = sum1 + zeroes1 - sum2;
            if(zeroes2 > diff || (diff != 0 && zeroes2 == 0))
                return -1;
            return sum1 + zeroes1;
        }
    
    }
}