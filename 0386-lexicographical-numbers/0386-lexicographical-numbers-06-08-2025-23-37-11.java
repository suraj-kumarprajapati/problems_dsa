class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();

        fun(0, n, ans);

        return ans;
    }

    public void fun(int num, int n, List<Integer> ans) {
        if(num > n)
            return;

        if(num != 0)
            ans.add(num);

        int start = num != 0 ? num * 10 : 1;
        int end = num != 0 ? start + 10 : 10;
        for(int i=start; i<end; i++) {
            fun(i, n, ans);
        }
    }
}