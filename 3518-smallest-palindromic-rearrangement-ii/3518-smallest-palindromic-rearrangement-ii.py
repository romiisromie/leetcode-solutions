import math

class Solution:
    def smallestPalindrome(self, s: str, k: int) -> str:
        freq = [0] * 26
        for char in s:
            freq[ord(char) - ord('a')] += 1
        
        odd_count = 0
        mid_char = ""
        for i in range(26):
            if freq[i] % 2 != 0:
                odd_count += 1
                mid_char = chr(i + ord('a'))
                if odd_count > 1:
                    return ""
        
        half_counts = [f // 2 for f in freq]
        half_len = sum(half_counts)
        
        LIMIT = 10**6 + 7
        
        def nCr_capped(n, r):
            if r < 0 or r > n:
                return 0
            if r == 0 or r == n:
                return 1
            if r > n // 2:
                r = n - r
            res = 1
            for i in range(1, r + 1):
                res = res * (n - i + 1) // i
                if res >= LIMIT:
                    return LIMIT
            return res

        def count_permutations(counts):
            total = sum(counts)
            res = 1
            for c in counts:
                if c > 0:
                    res *= nCr_capped(total, c)
                    if res >= LIMIT:
                        return LIMIT
                    total -= c
            return res
            
        if count_permutations(half_counts) < k:
            return ""
            
        res_half = []
        for _ in range(half_len):
            for i in range(26):
                if half_counts[i] > 0:
                    half_counts[i] -= 1
                    perms = count_permutations(half_counts)
                    if k <= perms:
                        res_half.append(chr(i + ord('a')))
                        break
                    else:
                        k -= perms
                        half_counts[i] += 1
                        
        left = "".join(res_half)
        return left + mid_char + left[::-1]