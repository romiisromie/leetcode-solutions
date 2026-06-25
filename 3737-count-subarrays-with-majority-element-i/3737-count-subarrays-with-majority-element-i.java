class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int targetCount = 0;
            int totalCount = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    targetCount++;
                }
                totalCount++;

                if (targetCount > totalCount / 2) {
                    count++;
                }
            }
        }

        return count;
    }
}