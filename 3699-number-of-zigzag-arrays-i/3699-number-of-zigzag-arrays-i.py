class Solution(object):
    def zigZagArrays(self, n, l, r):
        MOD = 10**9 + 7
        m = r - l + 1
        
        if m <= 1:
            return 0
            
        dp_up = [1] * m
        dp_down = [1] * m
        
        for _ in range(n - 1):
            next_up = [0] * m
            next_down = [0] * m
            
            curr_sum = 0
            for j in range(m):
                next_up[j] = curr_sum % MOD
                curr_sum += dp_down[j]
                
            curr_sum = 0
            for j in range(m - 1, -1, -1):
                next_down[j] = curr_sum % MOD
                curr_sum += dp_up[j]
                
            dp_up = next_up
            dp_down = next_down
            
        return (sum(dp_up) + sum(dp_down)) % MOD