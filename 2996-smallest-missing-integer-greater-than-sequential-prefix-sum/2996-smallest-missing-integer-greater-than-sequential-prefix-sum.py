class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        # Step 1: Find the length of the longest sequential prefix
        i = 1
        while i < len(nums) and nums[i] == nums[i - 1] + 1:
            i += 1
            
        # Step 2: Calculate the prefix sum
        prefix_sum = sum(nums[:i])
        
        # Step 3: Find the smallest missing integer >= prefix_sum
        num_set = set(nums)
        while prefix_sum in num_set:
            prefix_sum += 1
            
        return prefix_sum