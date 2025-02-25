class Solution {


    public int numOfSubarrays(int[] arr) {
        // return sol1(arr);

        return sol2(arr);
    }
    

    // using dp
    public int sol2(int[] arr)  {
        int n = arr.length;
        int ans = 0;
        int mod = (int) 1e9 + 7;

        // it represents, no. of subarrays ending at index i having even sum
        int[] dpEven = new int[n];
        // it represents, no. of subarrays ending at index i having odd sum
        int[] dpOdd = new int[n];
        if(arr[0] % 2 == 0) {
            dpEven[0] = 1;
        }
        else {
            dpOdd[0] = 1;
        }

        for(int i=1; i<n; i++) {
            if(arr[i] % 2 == 0) {
                dpEven[i] = (1 + dpEven[i-1]) % mod;
                dpOdd[i] = dpOdd[i-1];
            }
            else {
                dpEven[i] = dpOdd[i-1];
                dpOdd[i] = (dpEven[i-1] + 1) % mod;
            }
        }

        for(int i=0; i<n; i++) {
            ans = (ans + dpOdd[i]) % mod;
        }

        return ans;

    }


    public int sol1(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int evenSubSumCount = 1;
        int oddSubSumCount = 0;
        int prefixSum = 0;
        int mod = (int) 1e9 + 7;
        for(int i=0; i<n; i++) {
            prefixSum += arr[i];

            if(prefixSum % 2 == 0) {
                ans = (ans + oddSubSumCount) % mod;
                evenSubSumCount += 1;
            }
            else {
                ans = (ans + evenSubSumCount) % mod;
                oddSubSumCount += 1;
            }
        }

        return ans;
    }
}