class Solution:
    def reverseDegree(self, s: str) -> int:
        total_sum = 0
        for index, char in enumerate(s, start=1):
            rev_alphabet_pos = 26 - (ord(char) - ord('a'))
            total_sum += index * rev_alphabet_pos
        return total_sum