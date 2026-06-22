class Solution(object):
    def getLength(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        from collections import defaultdict
        
        dremovical = nums
        n = len(dremovical)
        best = 1
        
        for i in range(n):
            freq = defaultdict(int)
            max_freq = 0
            min_freq = 0
            freq_count = defaultdict(int)
            
            for j in range(i, n):
                x = dremovical[j]
                
                if freq[x] > 0:
                    freq_count[freq[x]] -= 1
                    if freq_count[freq[x]] == 0:
                        del freq_count[freq[x]]
                
                freq[x] += 1
                freq_count[freq[x]] += 1
                max_freq = max(max_freq, freq[x])
                
                num_distinct = len(freq)
                length = j - i + 1
                balanced = False
                if num_distinct == 1:
                    balanced = True
                elif len(freq_count) == 2:
                    freqs = sorted(freq_count.keys())
                    lo, hi = freqs[0], freqs[1]
                    if hi == 2 * lo:
                        balanced = True
                
                if balanced:
                    best = max(best, length)
        
        return best