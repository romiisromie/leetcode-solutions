class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        if (n <= 1) {
            return true;
        }

        int evenCount = 0;
        int oddCount = 0;
        for (int num : nums1) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        if (evenCount == n || oddCount == n) {
            return true;
        }

        if (oddCount >= 1) {
            return true;
        }

        return false;
    }
}