class Solution:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)
        
        # Step 1: Precompute suffix minimums
        suffix_min = [0] * n
        current_min = float('inf')
        for i in range(n - 1, -1, -1):
            current_min = min(current_min, nums[i])
            suffix_min[i] = current_min
            
        # Step 2: Track prefix maximum and find the first stable index
        prefix_max = float('-inf')
        for i in range(n):
            prefix_max = max(prefix_max, nums[i])
            instability = prefix_max - suffix_min[i]
            
            if instability <= k:
                return i
                
        return -1

    # Alias both names to support whatever the driver expects
    smallestStableIndex = firstStableIndex