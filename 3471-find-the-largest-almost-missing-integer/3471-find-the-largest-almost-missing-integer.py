from collections import defaultdict
from typing import List

class Solution:
    def largestInteger(self, nums: List[int], k: int) -> int:
        subarray_counts = defaultdict(int)
        
        # Slide a window of size k across the array
        for i in range(len(nums) - k + 1):
            # Convert to a set to count each number only once per subarray
            window_elements = set(nums[i:i + k])
            for num in window_elements:
                subarray_counts[num] += 1
                
        # Filter numbers that appear in exactly 1 subarray and find the max
        max_almost_missing = -1
        for num, count in subarray_counts.items():
            if count == 1:
                max_almost_missing = max(max_almost_missing, num)
                
        return max_almost_missing