class Solution {
    public int findTheWinner(int n, int k) {
        // return sol1(n, k);

        return sol2(n, k) + 1;
    }

    public int sol2(int n, int k) {
        if(n == 1)
            return 0;

        return (sol2(n-1, k) + k) % n;
    }

    // time -> O(n*k), space -> O(n)
    public int sol1(int n, int k) {
        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<=n; i++) {
            q.add(i);
        }

        while(q.size() > 1) {
            for(int i=1; i<k; i++) {
                q.add(q.peek());
                q.poll();
            }

            q.poll();
        }

        return q.poll();
    }
}