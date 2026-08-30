class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        # If total '1's in s is less than k, no valid substring exists
        if s.count('1') < k:
            return ""
        
        n = len(s)
        left = 0
        ones_count = 0
        best_sub = ""
        min_len = float('inf')
        
        for right in range(n):
            if s[right] == '1':
                ones_count += 1
                
            # Shrink window from the left to remove leading '0's when we reach k ones
            while ones_count == k:
                # Shrink left pointer past any leading '0's
                while s[left] == '0':
                    left += 1
                    
                curr_len = right - left + 1
                curr_sub = s[left : right + 1]
                
                # Update best substring if shorter or lexicographically smaller
                if curr_len < min_len:
                    min_len = curr_len
                    best_sub = curr_sub
                elif curr_len == min_len:
                    if curr_sub < best_sub:
                        best_sub = curr_sub
                        
                # Move left past the first '1' to look for next potential windows
                if s[left] == '1':
                    ones_count -= 1
                left += 1
                
        return best_sub