import math
from itertools import combinations
from typing import List

class Solution:
    def findKthSmallest(self, coins: List[int], k: int) -> int:
        n = len(coins)
        
        # Precompute LCMs for all combinations of coins
        # comb_lcms[size] stores list of LCMs for subsets of that size
        comb_lcms = []
        for r in range(1, n + 1):
            lcms = []
            for comb in combinations(coins, r):
                lcm_val = comb[0]
                for coin in comb[1:]:
                    lcm_val = (lcm_val * coin) // math.gcd(lcm_val, coin)
                lcms.append(lcm_val)
            comb_lcms.append(lcms)

        # Helper function to count numbers <= M divisible by at least one coin
        def count_valid(M: int) -> int:
            total = 0
            for r in range(n):
                sign = 1 if r % 2 == 0 else -1
                for lcm_val in comb_lcms[r]:
                    total += sign * (M // lcm_val)
            return total

        # Binary Search
        left, right = 1, min(coins) * k
        ans = right

        while left <= right:
            mid = (left + right) // 2
            if count_valid(mid) >= k:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans