class Solution {
    public int minMoves(int[] nums) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return (int) (sum - (long) min * nums.length);
    }
}