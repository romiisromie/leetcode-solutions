from collections import defaultdict
from typing import List

class Solution:
    def maxSubarrayLength(self, nums: List[int], k: int) -> int:
        freq = defaultdict(int)
        left = 0
        max_len = 0
        
        for right in range(len(nums)):
            # Expand window by including nums[right]
            freq[nums[right]] += 1
            
            # Shrink window from the left until frequency of nums[right] <= k
            while freq[nums[right]] > k:
                freq[nums[left]] -= 1
                left += 1
                
            # Update maximum valid length
            max_len = max(max_len, right - left + 1)
            
        return max_len