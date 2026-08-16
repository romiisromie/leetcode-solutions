class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if not strs:
            return ""

        for i in range(len(strs[0])):
            char = strs[0][i]
            for j in range(1, len(strs)):
                # If i reaches out of bounds for strs[j] or character mismatches
                if i == len(strs[j]) or strs[j][i] != char:
                    return strs[0][:i]

        return strs[0]