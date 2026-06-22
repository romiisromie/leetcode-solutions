class Solution(object):
    def minCost(self, grid, k):
        m, n = len(grid), len(grid[0])
        max_val = 0
        for row in grid:
            for val in row:
                if val > max_val:
                    max_val = val
                    
        dp = [[float('inf')] * n for _ in range(m)]
        dp[0][0] = 0
        
        for i in range(m):
            for j in range(n):
                if i > 0:
                    dp[i][j] = min(dp[i][j], dp[i-1][j] + grid[i][j])
                if j > 0:
                    dp[i][j] = min(dp[i][j], dp[i][j-1] + grid[i][j])
                    
        for _ in range(k):
            suf = [float('inf')] * (max_val + 2)
            for i in range(m):
                for j in range(n):
                    v = grid[i][j]
                    if dp[i][j] < suf[v]:
                        suf[v] = dp[i][j]
                        
            for v in range(max_val, -1, -1):
                if suf[v+1] < suf[v]:
                    suf[v] = suf[v+1]
                    
            new_dp = [[float('inf')] * n for _ in range(m)]
            for i in range(m):
                for j in range(n):
                    new_dp[i][j] = min(dp[i][j], suf[grid[i][j]])
                    
            for i in range(m):
                for j in range(n):
                    if i > 0:
                        new_dp[i][j] = min(new_dp[i][j], new_dp[i-1][j] + grid[i][j])
                    if j > 0:
                        new_dp[i][j] = min(new_dp[i][j], new_dp[i][j-1] + grid[i][j])
            dp = new_dp
            
        return dp[m-1][n-1]