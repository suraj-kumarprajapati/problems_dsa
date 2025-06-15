class Solution {
    public int maxDiff(int num) {
        int leftMostDigit = -1; // left most digit
        int leftMostSecondMaxDigit = -1; // in case left most digit is already 9
        int leftMostSecondMinDigit = -1; // in case left most digit is alreay 1 (non zero)
        int temp = num;
        while(temp > 0) {
            int rem = temp % 10;
            leftMostDigit = rem;

            if(rem != 9) 
                leftMostSecondMaxDigit = rem;

            if(rem != 1 && rem != 0)
                leftMostSecondMinDigit = rem;

            temp = temp / 10;
        }

        // for max number -> x1, y1
        // for min number -> x2, y2
        int x1 = leftMostDigit;  
        int x2 = leftMostDigit; // for min number
        int y1 = 9;
        int y2 = 1;

        if(leftMostDigit == 9) {
            x1 = leftMostSecondMaxDigit;
        }
        else if(leftMostDigit == 1) {
            x2 = leftMostSecondMinDigit;
            y2 = 0;
        }


        // apply operations on num to get max and min possible number
        // for max -> replace the x1 digit to y1
        // for min -> replace the x2 digit to y2
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