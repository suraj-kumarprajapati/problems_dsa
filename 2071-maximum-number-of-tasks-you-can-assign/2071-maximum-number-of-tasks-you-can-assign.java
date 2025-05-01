class Solution {
    private Map<String, Integer> dp;
    private int n;
    private int m;
    private int pills;
    private int strength;

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        // return sol1(tasks, workers, pills, strength);

        return sol2(tasks, workers, pills, strength);
    }

    private int sol2(int[] tasks, int[] workers, int pills, int strength) {
        int n = tasks.length;
        int m = workers.length;

        Arrays.sort(tasks);
        Arrays.sort(workers);

        int ans = 0;
        int left = 1;
        int right = Math.min(n, m);

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(canFinish(tasks, workers, pills, strength, mid)) {
                ans = mid;
                left += 1;
            }
            else {
                right -= 1;
            }
        }

        return ans;
    }

    private boolean canFinish(int[] tasks, int[] workers, int pills, int strength, int k) {
        // check if k smallest tasks can be finished by k workers having k greatest strength

        int p = pills;
        int m = workers.length;

        Deque<Integer> availWorkers = new ArrayDeque<>();

        int j = m-1;
        for(int i = k-1; i >= 0; i--) {
            while(j >= m - k && workers[j] + strength >= tasks[i]) {
                availWorkers.addFirst(workers[j]);
                j -= 1;
            }

            if(availWorkers.isEmpty()) 
                return false;
            else if(availWorkers.getLast() >= tasks[i]) {
                availWorkers.pollLast();
            }
            else {
                if(p == 0) 
                    return false;
                p -= 1;
                availWorkers.pollFirst();
            }
        }

        return true;
    }


    // using memoization(dp) + bit masking
    // time -> O(n * pills * (2 ^ m) * m * m)
    // space -> O(n * pills * 2^m + n)
    public int sol1(int[] tasks, int[] workers, int pills, int strength) {
        this.n = tasks.length;
        this.m = workers.length;
        this.pills = pills;
        this.strength = strength;
 
        this.dp = new HashMap<>();
        char[] mask = new char[m];
        for(int i=0; i<m; i++) {
            mask[i] = '0';
        }

        return helper(0, pills, tasks, workers, mask);
    }

    private int helper(int taskInd, int remPills, int[] tasks, int[] workers, char[] mask) {

        // base case
        if(taskInd == n) {
            return 0;
        }

        String key = taskInd + ", " + remPills + new String(mask);
        if(dp.containsKey(key)) {
            return dp.get(key);
        }


        int maxRes = 0;
        boolean assigned = false;
        for(int wInd = 0; wInd < m; wInd += 1) {
            // if worker had already performed some work
            if(workers[wInd] == -1) 
                continue;

            int currRes = 0;
            int workerStrength = workers[wInd];

            int reqPills = 0;
            if(tasks[taskInd] > workerStrength) {
                int extraStrengthReq = tasks[taskInd] - workers[wInd];
                reqPills = (int) Math.ceil((double)extraStrengthReq / (double)strength);
            }
            
            if (reqPills <= 1 && workers[wInd] + remPills * strength >= tasks[taskInd]) {
                mask[wInd] = '1';
                assigned = true;
                workers[wInd] = -1;
                currRes = 1 + helper(taskInd + 1, remPills - reqPills, tasks, workers, mask);
                // backtrack
                workers[wInd] = workerStrength;
                mask[wInd] = '0';
            }

            maxRes = Math.max(maxRes, currRes);            
        }

        if(!assigned) {
            int tempRes = helper(taskInd + 1, remPills, tasks, workers, mask);
            maxRes = Math.max(maxRes, tempRes);
        }

        dp.put(key, maxRes);
        return maxRes;
    }
}