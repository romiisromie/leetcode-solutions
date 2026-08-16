class Solution:
    def minimumPushes(self, word: str) -> int:
        n = len(word)
        ans = 0
        
        if n > 0:
            ans += min(n, 8) * 1
        if n > 8:
            ans += min(n - 8, 8) * 2
        if n > 16:
            ans += min(n - 16, 8) * 3
        if n > 24:
            ans += (n - 24) * 4
            
        return ans