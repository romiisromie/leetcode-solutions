class Solution(object):
    def checkGoodInteger(self, n):
        """
        :type n: int
        :rtype: bool
        """
        digits = [int(d) for d in str(n)]
        return sum(d**2 for d in digits) - sum(digits) >= 50