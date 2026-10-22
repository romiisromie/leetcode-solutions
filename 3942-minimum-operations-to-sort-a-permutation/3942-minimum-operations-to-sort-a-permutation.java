class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int idx0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }

        boolean isNormal = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isNormal = false;
                break;
            }
        }

        if (isNormal) {
            int op1 = idx0;
            int op2 = 1 + (n - idx0) % n + 1;
            return Math.min(op1, op2);
        }

        boolean isReversed = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx0 - i + n) % n] != i) {
                isReversed = false;
                break;
            }
        }

        if (isReversed) {
            int op1 = (idx0 + 1) % n + 1;
            int op2 = n - idx0;
            return Math.min(op1, op2);
        }

        return -1;
    }
}