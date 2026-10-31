import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(squares);
        
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalSum += squares[right];
                right--;
            } else {
                totalSum -= squares[left];
                left++;
            }
        }
        
        return totalSum;
    }
}