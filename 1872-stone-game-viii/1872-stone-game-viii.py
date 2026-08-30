from itertools import accumulate
from typing import List

class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        # Compute prefix sums
        pref = list(accumulate(stones))
        n = len(stones)
        
        # Base case: the last possible choice is index n - 1 (taking all stones)
        dp = pref[-1]
        
        # Iterate backwards from index n - 2 down to 1
        for i in range(n - 2, 0, -1):
            dp = max(dp, pref[i] - dp)
            
        return dp