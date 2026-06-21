class Solution(object):
    def findErrorNums(self, nums):
        n = len(nums)
        seen = set()
        duplicate = -1
        
        for num in nums:
            if num in seen:
                duplicate = num
            seen.add(num)
            
        expected_sum = n * (n + 1) // 2
        actual_unique_sum = sum(seen)
        missing = expected_sum - actual_unique_sum
        
        return [duplicate, missing]