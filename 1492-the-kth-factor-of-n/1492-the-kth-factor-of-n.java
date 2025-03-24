class Solution {
    public int kthFactor(int n, int k) {
        return sol1(n, k);
    }

    public int sol2(int n, int k) {
        for(int i=1; i<=(int) Math.sqrt(n); i++) {
            if(n % i == 0 && --k == 0) 
                return i;
        }

        for(int i=(int) Math.sqrt(n); i>=1; i--) {

            if(i * i == n)
                continue;

            if(n % i == 0 && --k == 0)
                return n/i;
        }

        return -1;
    }

    public int sol1(int n, int k) {
        for(int i=1; i<=n/2; i++) {
            if(n % i == 0 && --k == 0) {
                return i;
            }
        }

        return k == 1 ? n : -1;
    }
}