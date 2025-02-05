class Solution {
    public int trap(int[] height) {
        // return sol1(height);

        return sol2(height);
    }

    public int sol2(int[] height) {
        int n = height.length;
        int vol = 0;

        int i = 0;
        int j = n-1;
        int leftMax = 0;
        int rightMax = 0;
        while(i < j) {
            if(height[i] <= height[j]) {
                if( leftMax > height[i] ) {
                    vol += (leftMax - height[i]);
                }

                leftMax = Math.max(leftMax, height[i]);
                i += 1;
            }
            else {
                if(rightMax > height[j]) {
                    vol += (rightMax - height[j]);
                }

                rightMax = Math.max(rightMax, height[j]);
                j -= 1;
            }
        }

        return vol;
    }

    // time -> O(n)
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