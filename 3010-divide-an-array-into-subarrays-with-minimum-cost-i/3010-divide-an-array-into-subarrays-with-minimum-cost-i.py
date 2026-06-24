class Solution(object):
    def minimumCost(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        first = nums[0]
        remaining = sorted(nums[1:])
        return first + remaining[0] + remaining[1]