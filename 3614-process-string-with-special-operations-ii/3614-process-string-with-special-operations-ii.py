class Solution(object):
    def processStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        sizes = []
        curr_size = 0
        
        for char in s:
            if char >= 'a' and char <= 'z':
                curr_size += 1
            elif char == '*':
                if curr_size > 0:
                    curr_size -= 1
            elif char == '#':
                curr_size *= 2
            elif char == '%':
                pass
            sizes.append(curr_size)
            
        if k < 0 or k >= curr_size:
            return "."
            
        for i in range(len(s) - 1, -1, -1):
            char = s[i]
            if char >= 'a' and char <= 'z':
                if k == sizes[i] - 1:
                    return char
            elif char == '*':
                pass
            elif char == '#':
                prev_size = sizes[i] / 2
                if k >= prev_size:
                    k -= prev_size
            elif char == '%':
                prev_size = sizes[i]
                k = prev_size - 1 - k
                
        return "."