class Solution {
    public int trap(int[] height) {
        return sol1(height);
    }

    public int sol1(int[] height) {
        int n = height.length;

        int[] preMax = new int[n];
        preMax[0] = height[0];
        int[] suffMax = new int[n];
        suffMax[n-1] = height[n-1];


        for(int i=1; i<n; i++) {
            preMax[i] = Math.max(preMax[i-1], height[i]);
        }

        for(int i=n-2; i>=0; i--) {
            suffMax[i] = Math.max(suffMax[i+1], height[i]);
        }

        int vol = 0;
        for(int i=1; i<=n-2; i++) {
            int leftMax = preMax[i];
            int rightMax = suffMax[i];

            if(leftMax > height[i] && rightMax > height[i]) {
                vol += Math.min(leftMax, rightMax) - height[i];
            }
        }

        return vol;
    }

    
}