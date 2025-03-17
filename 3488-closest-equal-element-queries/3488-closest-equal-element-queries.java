class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i<n; i++) {
            if(!map.containsKey(nums[i]))
                map.put(nums[i], new ArrayList<>());

            map.get(nums[i]).add(i);
        }

        int m = queries.length;
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<m; i++) {
            int ind = queries[i];
            int num = nums[ind];

            if(map.get(num).size() == 1) {
                ans.add(-1);
                continue;
            }

            List<Integer> arr = map.get(num);
            int l = 0;
            int h = map.get(num).size()-1;
            int ind2 = 0;
            while(l <= h) {
                int mid = l + (h-l)/2;

                if(arr.get(mid) == ind) {
                    ind2 = mid;
                    break;
                }
                else if(ind > arr.get(mid)) {
                    l = mid + 1;
                }
                else {
                    h = mid - 1;
                }
            }

            int minDist = Integer.MAX_VALUE;


            // left direction
            if(ind2 - 1 >= 0) {
                int lDist = Math.abs(arr.get(ind2-1) - arr.get(ind2));
                int rDist = n - lDist;
                minDist = Math.min(minDist, lDist);
                minDist = Math.min(minDist, rDist);
            }
            else {
                int lDist = Math.abs(arr.get(arr.size()-1) - arr.get(ind2));
                int rDist = n - lDist;
                minDist = Math.min(minDist, lDist);
                minDist = Math.min(minDist, rDist); 
            }

            // right dist
            if(ind2 + 1 < arr.size()) {
                int rDist = Math.abs(arr.get(ind2+1) - arr.get(ind2));
                int lDist = n - rDist;
                minDist = Math.min(minDist, lDist);
                minDist = Math.min(minDist, rDist);
            }
            else {
                int rDist = Math.abs(arr.get(0) - arr.get(ind2));
                int lDist = n - rDist;
                minDist = Math.min(minDist, lDist);
                minDist = Math.min(minDist, rDist);
            }

            ans.add(minDist);
        }

        return ans;        
    }
}