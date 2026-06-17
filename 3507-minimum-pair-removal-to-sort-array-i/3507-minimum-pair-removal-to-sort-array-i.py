class Solution(object):
    def minimumPairRemoval(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        def is_sorted(arr):
            for i in range(len(arr) - 1):
                if arr[i] > arr[i + 1]:
                    return False
            return True

        operations = 0
        while not is_sorted(nums):
            min_sum = float('inf')
            min_idx = -1
            
            for i in range(len(nums) - 1):
                pair_sum = nums[i] + nums[i + 1]
                if pair_sum < min_sum:
                    min_sum = pair_sum
                    min_idx = i
                    
            nums[min_idx] = min_sum
            nums.pop(min_idx + 1)
            operations += 1
            
        return operations
