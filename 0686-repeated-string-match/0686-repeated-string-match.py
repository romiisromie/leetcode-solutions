class Solution(object):
    def repeatedStringMatch(self, a, b):
        min_repeats = (len(b) + len(a) - 1) // len(a)
        
        if b in a * min_repeats:
            return min_repeats
            
        if b in a * (min_repeats + 1):
            return min_repeats + 1
            
        return -1