public class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int xten = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                xten++;
            } else {
                if (xten > 0 && five > 0) {
                    xten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}