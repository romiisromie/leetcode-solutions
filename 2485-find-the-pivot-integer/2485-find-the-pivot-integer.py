class Solution(object):
    def pivotInteger(self, n):
        """
        :type n: int
        :rtype: int
        """
        total_sum = (n * (n + 1)) / 2
        
        current_sum = 0
        for i in range(1, n + 1):
            current_sum += i
            if current_sum == (total_sum - current_sum + i):
                return i
                
        return -1
