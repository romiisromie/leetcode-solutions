public class Solution {
    public int maximumPrimeDifference(int[] nums) {
        int firstPrimeIdx = -1;
        int lastPrimeIdx = -1;

        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                if (firstPrimeIdx == -1) {
                    firstPrimeIdx = i;
                }
                lastPrimeIdx = i;
            }
        }

        return lastPrimeIdx - firstPrimeIdx;
    }

    private boolean isPrime(int val) {
        if (val <= 1) {
            return false;
        }
        if (val == 2 || val == 3) {
            return true;
        }
        if (val % 2 == 0 || val % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= val; i += 6) {
            if (val % i == 0 || val % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}