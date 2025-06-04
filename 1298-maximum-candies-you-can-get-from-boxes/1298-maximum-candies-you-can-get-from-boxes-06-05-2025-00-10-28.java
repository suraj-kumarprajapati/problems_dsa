class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;

        int[] opened = new int[n];
        int[] openable = new int[n];
        Queue<Integer> q = new LinkedList<>();

        for(int box : initialBoxes) {
            openable[box] = 1;
            if(status[box] == 1) {
                q.add(box);
                opened[box] = 1;
            }
        }

        int ans = 0;
        while(!q.isEmpty()) {
            int bigBox = q.poll();
            ans += candies[bigBox];

            for(int keyedBox : keys[bigBox]) {
                status[keyedBox] = 1;

                if(opened[keyedBox] == 0 && openable[keyedBox] == 1) {
                    opened[keyedBox] = 1;
                    q.add(keyedBox);
                }
            }


            for(int smallBox : containedBoxes[bigBox]) {
                openable[smallBox] = 1;
                if(status[smallBox] == 1 && opened[smallBox] == 0) {
                    opened[smallBox] = 1;
                    q.add(smallBox);
                }
            }
        }

        return ans;
    }
}