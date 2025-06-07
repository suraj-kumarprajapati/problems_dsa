class Solution {
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;

        // this represent the no. of 2's in prime factorization of nums[i]
        // or, the no. of powers of 2 (for nums[i])
        int[] po = new int[n];
        for(int i=0; i<n; i++) {
            int temp = nums[i];
            while(temp % 2 == 0) {
                po[i] += 1;
                temp = temp / 2;
            }
        }

        long ans = 0;
        for(int l=0; l<n; l++) {
            ans = Math.max(ans, 2*nums[l] * 1l); 
            long g = nums[l] * 1l; // gcd of subarray[l...r]
            int min = po[l];  // minimum no. of powers of 2 in subarray[l...r]
            int[] freq = new int[32];  // freq of powers of 2 in subarray[l...r]
            freq[po[l]] += 1;
            for(int r=l+1; r<n; r++) {
                g = gcd(g, (long) nums[r]);
                freq[po[r]] += 1;
                min = Math.min(min, po[r]);

                long score = 0;
                if(freq[min] <= k) {
                    score = (r - l + 1) * g * 2 * 1l;
                }
                else {
                    score = (r - l + 1) * g * 1l;
                }

                ans = Math.max(ans, score);
            }
        }

        return ans;
    }

    public long gcd(long a, long b) {
        long max = Math.max(a, b);
        long min = Math.min(a, b);

        a = max;
        b = min;

        while(b > 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }

        return a;
    }
}