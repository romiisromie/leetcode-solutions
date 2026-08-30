from collections import Counter

class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        n = len(s)
        freq_s = Counter(s)
        
        # Try to match the prefix target[:i]
        # then place a character strictly larger than target[i] at index i
        for i in range(n - 1, -1, -1):
            prefix_counts = Counter(target[:i])
            
            # Check if target[:i] can be formed by s
            if any(prefix_counts[ch] > freq_s[ch] for ch in prefix_counts):
                continue
                
            remaining = freq_s - prefix_counts
            
            # Find the smallest character at index i that is > target[i]
            target_char = target[i]
            candidates = sorted([ch for ch in remaining if ch > target_char])
            
            if candidates:
                chosen_char = candidates[0]
                remaining[chosen_char] -= 1
                
                # Append the rest of the characters sorted in ascending order
                suffix = []
                for ch in sorted(remaining.keys()):
                    suffix.append(ch * remaining[ch])
                    
                return target[:i] + chosen_char + "".join(suffix)
                
        return ""