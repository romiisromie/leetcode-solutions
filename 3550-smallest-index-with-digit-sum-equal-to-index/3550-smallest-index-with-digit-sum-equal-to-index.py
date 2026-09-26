class Solution:
    def smallestIndex(self, nums: list[int]) -> int:
        for i, num in enumerate(nums):
            # Calculate sum of digits of nums[i]
            digit_sum = sum(int(digit) for digit in str(abs(num)))
            
            if digit_sum == i:
                return i
                
        return -1