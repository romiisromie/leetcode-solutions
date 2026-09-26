class Solution:
    def countCommas(self, n: int) -> int:
        if n < 1000:
            return 0

        total_commas = 0
        d = 1
        
        while 10**(d - 1) <= n:
            start = 10**(d - 1)
            end = min(n, 10**d - 1)
            count = end - start + 1
            
            # Commas per number with 'd' digits
            commas_per_num = max(0, (d - 1) // 3)
            total_commas += count * commas_per_num
            
            d += 1
            
        return total_commas