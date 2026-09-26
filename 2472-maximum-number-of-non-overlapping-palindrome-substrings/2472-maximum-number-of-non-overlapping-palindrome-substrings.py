class Solution:
    def maxPalindromes(self, s: str, k: int) -> int:
        n = len(s)
        count = 0
        last_end = -1  # End index of the last chosen palindrome

        for i in range(n):
            # Check for palindrome of length k
            if i - k + 1 > last_end:
                sub1 = s[i - k + 1 : i + 1]
                if sub1 == sub1[::-1]:
                    count += 1
                    last_end = i
                    continue

            # Check for palindrome of length k + 1
            if i - k > last_end:
                sub2 = s[i - k : i + 1]
                if sub2 == sub2[::-1]:
                    count += 1
                    last_end = i

        return count