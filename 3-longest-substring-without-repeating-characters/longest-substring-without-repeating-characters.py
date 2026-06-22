class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        seen = {}
        max_len = 0
        start = 0
        
        for end in range(len(s)):
            char = s[end]
            
            if char in seen and seen[char] >= start:
                start = seen[char] + 1
                
            seen[char] = end
            max_len = max(max_len, end - start + 1)
            
        return max_len