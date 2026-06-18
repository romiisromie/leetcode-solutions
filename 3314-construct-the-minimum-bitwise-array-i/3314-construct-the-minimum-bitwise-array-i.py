class Solution(object):
    def minBitwiseArray(self, nums):
        ans = []
        for num in nums:
            if num == 2:
                ans.append(-1)
                continue
            
            for x in range(num):
                if x | (x + 1) == num:
                    ans.append(x)
                    break
        return ans
