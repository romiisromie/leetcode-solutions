from typing import List

class Solution:
    def lexicographicallySmallestArray(self, nums: List[int], limit: int) -> List[int]:
        n = len(nums)
        # Pair each element with its original index and sort by element value
        pairs = sorted((nums[i], i) for i in range(n))
        
        ans = [0] * n
        i = 0
        
        while i < n:
            j = i + 1
            # Find all elements in the same connected group
            while j < n and pairs[j][0] - pairs[j - 1][0] <= limit:
                j += 1
                
            # Extract indices for the current group and sort them ascendingly
            group_indices = sorted(pairs[k][1] for k in range(i, j))
            
            # Place the smallest values from the group into the smallest original indices
            for k in range(i, j):
                ans[group_indices[k - i]] = pairs[k][0]
                
            i = j
            
        return ans