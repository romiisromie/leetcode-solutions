from functools import reduce
from operator import xor
from typing import List

class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        total_xor = reduce(xor, nums)
        
        # Case 1: Entire array has a non-zero XOR
        if total_xor != 0:
            return len(nums)
        
        # Case 2: Total XOR is 0, check if any non-zero element exists
        if any(x != 0 for x in nums):
            return len(nums) - 1
            
        # Case 3: All elements are 0
        return 0