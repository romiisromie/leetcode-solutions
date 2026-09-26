class Solution:
    def distinctSubseqII(self, s: str) -> int:
        MOD = 10**9 + 7
        ends = [0] * 26
        
        for c in s:
            idx = ord(c) - ord('a')
            total = sum(ends) % MOD
            ends[idx] = (total + 1) % MOD
            
        return sum(ends) % MOD