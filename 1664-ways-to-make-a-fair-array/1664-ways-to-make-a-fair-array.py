class Solution(object):
    def waysToMakeFair(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        right_even = 0
        right_odd = 0
        for i, num in enumerate(nums):
            if i % 2 == 0:
                right_even += num
            else:
                right_odd += num
                
        left_even = 0
        left_odd = 0
        ans = 0
        
        for i, num in enumerate(nums):
            if i % 2 == 0:
                right_even -= num
            else:
                right_odd -= num
                
            if left_even + right_odd == left_odd + right_even:
                ans += 1
                
            if i % 2 == 0:
                left_even += num
            else:
                left_odd += num
                
        return ans