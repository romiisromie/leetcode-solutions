class Solution(object):
    def minBitwiseArray(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        ans = []
        for num in nums:
            if num == 2:
                ans.append(-1)
                continue
            
            for i in range(32):
                if not (num & (1 << i)):
                    first_unset_bit = i
                    break
            
            ans.append(num ^ (1 << (first_unset_bit - 1)))
            
        return ans
