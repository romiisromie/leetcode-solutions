from collections import Counter

class Solution:
    def minimumPushes(self, word: str) -> int:
        # Count frequency of each letter
        counts = Counter(word)
        
        # Sort frequencies in descending order
        freqs = sorted(counts.values(), reverse=True)
        
        ans = 0
        for i, freq in enumerate(freqs):
            # (i // 8) + 1 calculates the cost per push (1, 2, 3, or 4)
            push_cost = (i // 8) + 1
            ans += freq * push_cost
            
        return ans