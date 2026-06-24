class Solution(object):
    def minSubarray(self, nums, p):
        """
        :type nums: List[int]
        :type p: int
        :rtype: int
        """
        total_rem = sum(nums) % p
        if total_rem == 0:
            return 0
            
        n = len(nums)
        min_len = n
        current_sum = 0
        mod_map = {0: -1}
        
        for i, num in enumerate(nums):
            current_sum = (current_sum + num) % p
            target = (current_sum - total_rem + p) % p
            
            if target in mod_map:
                min_len = min(min_len, i - mod_map[target])
                
            mod_map[current_sum] = i
            
        return min_len if min_len < n else -1