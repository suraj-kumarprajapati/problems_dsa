class Solution {
    public double separateSquares(int[][] squares) {
        double minY = getMinY(squares);
        double maxY = getMaxY(squares);
        double precision = 1e-5;


        while(maxY - minY > precision) {
            double midY = minY + (maxY - minY) / 2;

            if( isLowerAreaLarger(squares, midY) ) {
                maxY = midY;
            }
            else {
                minY = midY;
            }
        }

        return minY;       
    }



    public boolean isLowerAreaLarger(int[][] squares, double midY) {
        double la = (double)0.0;
        double ua = (double)0.0;


        for(int[] square : squares) {
            double x = (double) square[0];
            double y = (double) square[1];
            double len = (double) square[2];

            if(y > midY) {
                ua += len * len;
            }
            else if(y + len < midY) {
                la += len * len;
            }
            else {
                double llen = midY - y;
                double ulen = y + len - midY;
                la += len * llen;
                ua += len * ulen;
            }
        }



        return (la >= ua);
    }

    public double getMinY(int[][] squares) {
        double minY = 2e9;
        for(int[] square : squares) {
            double y = (double) square[1];
            minY = Math.min(minY, y);
        }

        return minY;
    }

    public double getMaxY(int[][] squares) {
        double maxY = 0.0d;
        for(int[] square : squares) {
            double y = (double) square[1] + (double) square[2];
            maxY = Math.max(maxY, y);
        }

        return maxY;
    }
}