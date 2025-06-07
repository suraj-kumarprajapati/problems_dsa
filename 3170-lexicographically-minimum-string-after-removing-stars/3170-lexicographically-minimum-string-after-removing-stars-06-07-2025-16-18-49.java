class Pair {
    char ch;
    int ind;

    public Pair(char ch, int ind) {
        this.ch = ch;
        this.ind = ind;
    }
}


class Solution {
    public String clearStars(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {
            if(p1.ch > p2.ch) {
                return 1;
            }
            else if(p1.ch < p2.ch) {
                return -1;
            }
            else {
                return p2.ind - p1.ind;
            }
        });


        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);

            if(ch != '*') {
                pq.add(new Pair(ch, i));
                continue;
            }
            else {
                Pair p = pq.poll();
                arr[p.ind] = ' ';
                arr[i] = ' ';
            }
        }

        StringBuilder str = new StringBuilder();
        for(char ch : arr) {
            if(ch == ' ')
                continue;
            str.append(ch);
        }

        return str.toString();
    }


}