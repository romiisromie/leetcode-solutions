from typing import List

class Solution:
    def uniformArray(self, nums1: List[int]) -> bool:
        # If all numbers are already even or all are odd, return True
        first_parity = nums1[0] % 2
        if all(x % 2 == first_parity for x in nums1):
            return True
        
        # If mixed parity, we can only succeed if the minimum element is odd
        min_val = min(nums1)
        return min_val % 2 != 0