class Solution {
    public int maxDiff(int num) {
        

        
        int x1 = 0; // left most digit
        int x2 = 0; // in case left most digit is already 1 
        int temp = num;
        int y1 = 9;
        int y2 = 1;
        while(temp > 0) {
            int rem = temp % 10;
            x1 = rem;
            if(x1 != 1 && x1 != 0)
                x2 = x1;
            temp = temp / 10;
        }

        if(x1 == 1) {
            y2 = 0;
        }
        else {
            x2 = x1;
        }

        // apply operations on num to get max and min possible number
        // for max -> replace the leftmost digit to 9
        // for min -> replace the leftmost digit to 1
        int a = 0;
        int b = 0;
        int pow = 1;
        
        temp = num;
        while(temp > 0 ) {
            int rem = temp % 10;
           
            if(rem == x1) {
                a = a + pow * y1;
            }
            else {
                a = a + pow * rem;
            }

            if(rem == x2) {
                b = b + pow * y2;
            }
            else {
                b = b + pow * rem;
            }

            pow *= 10;
            temp = temp / 10;
        }

        return a - b;      
    }
}