class Solution(object):
    def maxSideLength(self, mat, threshold):
        """
        :type mat: List[List[int]]
        :type threshold: int
        :rtype: int
        """
        m = len(mat)
        n = len(mat[0])
        
        prefix = [[0] * (n + 1) for _ in range(m + 1)]
        for r in range(1, m + 1):
            for c in range(1, n + 1):
                prefix[r][c] = (mat[r - 1][c - 1] + 
                                prefix[r - 1][c] + 
                                prefix[r][c - 1] - 
                                prefix[r - 1][c - 1])
        
        max_side = 0
        
        for r in range(1, m + 1):
            for c in range(1, n + 1):
                side = max_side + 1
                if r - side >= 0 and c - side >= 0:
                    current_sum = (prefix[r][c] - 
                                   prefix[r - side][c] - 
                                   prefix[r][c - side] + 
                                   prefix[r - side][c - side])
                    if current_sum <= threshold:
                        max_side = side
                        
        return max_side
