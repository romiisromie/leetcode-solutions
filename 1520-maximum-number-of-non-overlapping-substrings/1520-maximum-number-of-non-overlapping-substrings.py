class Solution:
    def maxNumOfSubstrings(self, s: str) -> list[str]:
        # Step 1: Record first and last occurrence for each character
        first = {}
        last = {}
        for i, ch in enumerate(s):
            if ch not in first:
                first[ch] = i
            last[ch] = i

        valid_intervals = []

        # Step 2: Find all minimal valid intervals
        for ch in first:
            left = first[ch]
            right = last[ch]
            
            i = left
            is_valid = True
            
            while i <= right:
                curr_ch = s[i]
                # If a character inside starts before 'left', invalid start
                if first[curr_ch] < left:
                    is_valid = False
                    break
                # Expand right boundary if needed
                right = max(right, last[curr_ch])
                i += 1

            if is_valid:
                valid_intervals.append((right, left))

        # Step 3: Sort valid intervals by end position
        valid_intervals.sort()

        # Step 4: Greedily pick non-overlapping substrings
        result = []
        prev_end = -1

        for right, left in valid_intervals:
            if left > prev_end:
                result.append(s[left : right + 1])
                prev_end = right

        return result