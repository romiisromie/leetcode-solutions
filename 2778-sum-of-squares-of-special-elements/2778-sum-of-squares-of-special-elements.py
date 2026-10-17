class Solution:
    def sumOfSquares(self, nums: list[int]) -> int:
        n = len(nums)
        total_sum = 0
        
        for i in range(n):
            # Check if 1-based index (i + 1) divides n
            if n % (i + 1) == 0:
                total_sum += nums[i] ** 2
                
        return total_sum