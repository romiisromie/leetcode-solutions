from collections import Counter

class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        n = len(s)
        m = n // 2
        freq = Counter(s)
        
        # Check odd frequencies
        odd_chars = [ch for ch, count in freq.items() if count % 2 != 0]
        if len(odd_chars) > 1:
            return ""
        
        mid_char = odd_chars[0] if odd_chars else ""
        
        # Available characters for the first half
        half_freq = Counter()
        for ch, count in freq.items():
            half_freq[ch] = count // 2

        def build_palindrome(first_half: str) -> str:
            second_half = first_half[::-1]
            if n % 2 != 0:
                return first_half + mid_char + second_half
            return first_half + second_half

        # Try to match prefix of target's first half of length i
        for i in range(m, -1, -1):
            prefix_counts = Counter(target[:i])
            
            # Check if target[:i] can be formed by half_freq
            if any(prefix_counts[ch] > half_freq[ch] for ch in prefix_counts):
                continue
                
            remaining = half_freq - prefix_counts
            
            # Case 1: i == m (Full first half match)
            if i == m:
                cand = build_palindrome(target[:m])
                if cand > target:
                    return cand
                continue
                
            # Case 2: Pick a character at index i strictly greater than target[i]
            target_char = target[i]
            candidates = sorted([ch for ch in remaining if ch > target_char])
            
            if candidates:
                chosen_char = candidates[0]
                remaining[chosen_char] -= 1
                
                # Build the smallest first half suffix
                suffix = []
                for ch in sorted(remaining.keys()):
                    suffix.append(ch * remaining[ch])
                    
                first_half = target[:i] + chosen_char + "".join(suffix)
                cand = build_palindrome(first_half)
                
                if cand > target:
                    return cand
                    
        return ""