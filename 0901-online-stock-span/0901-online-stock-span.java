class StockSpanner {
    Stack<int[]> st;
    int ind;

    public StockSpanner() {
        this.ind = -1;
        st = new Stack<>();
    }
    
    public int next(int price) {
        ind += 1;

        while(!st.isEmpty() && st.peek()[1] <= price) {
            st.pop();
        }

        int span = ind - (st.isEmpty() ? -1 : st.peek()[0]);
        st.push(new int[] {ind, price});
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */