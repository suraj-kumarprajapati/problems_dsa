class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        // sol1(0, n, ans);
        sol2(n, ans);
        return ans;
    }

    public void sol2(int n, List<Integer> ans) {
        int curr = 1;
        for(int i=0; i<n; i++) {
            ans.add(curr);

            if(curr*10 <= n) {
                curr *= 10;
            }
            else {
                while(curr % 10 == 9 || curr >= n) {
                    curr = curr / 10;
                }
                curr += 1;
            }
        }
    }

    public void sol1(int num, int n, List<Integer> ans) {
        if(num > n)
            return;

        if(num != 0)
            ans.add(num);

        int start = num != 0 ? num * 10 : 1;
        int end = num != 0 ? start + 10 : 10;
        for(int i=start; i<end; i++) {
            sol1(i, n, ans);
        }
    }
}