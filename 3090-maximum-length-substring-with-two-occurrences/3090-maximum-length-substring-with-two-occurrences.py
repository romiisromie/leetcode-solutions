from collections import defaultdict

class Solution:
    def maximumLengthSubstring(self, s: str) -> int:
        freq = defaultdict(int)
        left = 0
        max_len = 0
        
        for right in range(len(s)):
            # Add character at right pointer to current window
            char = s[right]
            freq[char] += 1
            
            # Shrink window from the left until character count is at most 2
            while freq[char] > 2:
                freq[s[left]] -= 1
                left += 1
                
            # Update maximum valid length
            max_len = max(max_len, right - left + 1)
            
        return max_len