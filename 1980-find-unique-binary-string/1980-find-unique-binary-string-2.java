class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // return sol1(nums);

        return sol2(nums);
    }


    // cantor's diagonal argument
    public String sol2(String[] nums) {
        StringBuilder res = new StringBuilder();
        for(int i=0; i<nums.length; i++) {
            char ch = nums[i].charAt(i);
            res.append(ch == '0' ? '1' : '0');
        }

        return res.toString();
    }

    public String sol1(String[] nums) {
        int n = nums.length;

        Set<Integer> uniqueNumbers = new HashSet<>();   
        for(String num : nums) {
            int dec = getDecimal(num);
            uniqueNumbers.add(dec);
        }

        int res = 0;
        for(int i=0; i<Math.pow(2, n); i++) {
            if(!uniqueNumbers.contains(i)) {
                res = i;
                break;
            }
        }

        return getBinary(res, n);
    }

    public String getBinary(int x, int n) {
        StringBuilder str = new StringBuilder();

        // base  case
        if(x == 0) {
            for(int i=0; i<n; i++) {
                str.append(0);
            }

            return str.toString();
        }

        while(x != 0) {
            int rem = (x & 1);
            str.append(rem);
            x = x >> 1;
        }

        while(str.length() != n) {
            str.append(0);
        }

        str.reverse();
        return str.toString();
    }


    public int getDecimal(String num) {
        int res = 0;
        int n = num.length();

        int mul = 1;
        for(int i=n-1; i>=0; i--) {
            char ch = num.charAt(i);
            int bit = (int) (ch - '0');
            res = res + bit*mul;
            mul *= 2;
        }

        return res;
    }

}