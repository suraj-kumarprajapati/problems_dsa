class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        return sol1(arr);
    }

    public int sol1(int[] arr) {
        int n = arr.length;

        Arrays.sort(arr);
        arr[0] = 1;

        for(int i=1; i<n; i++) {
           arr[i] = Math.min(arr[i-1] + 1, arr[i]);
        }

        return arr[n-1];
    }
}