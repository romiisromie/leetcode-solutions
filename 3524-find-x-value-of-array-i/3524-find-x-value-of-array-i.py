class Solution:
    def resultArray(self, nums: list[int], k: int) -> list[int]:
        result = [0] * k
        dp = {}  # remainder -> count of subarrays ending at current index

        for num in nums:
            next_dp = {}
            mod_val = num % k
            
            # Start a new subarray with just the current element
            next_dp[mod_val] = next_dp.get(mod_val, 0) + 1
            
            # Extend existing subarrays ending at the previous element
            for prev_rem, count in dp.items():
                new_rem = (prev_rem * mod_val) % k
                next_dp[new_rem] = next_dp.get(new_rem, 0) + count
            
            # Add counts from subarrays ending at this index to overall result
            for rem, count in next_dp.items():
                result[rem] += count
                
            dp = next_dp

        return result