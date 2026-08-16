class Solution:
    def validSequence(self, word1: str, word2: str) -> List[int]:
        n, m = len(word1), len(word2)
        
        # last[j] stores the maximum index in word1 from which 
        # word2[j:] can be formed as a subsequence
        last = [-1] * (m + 1)
        last[m] = n
        
        p = n - 1
        for j in range(m - 1, -1, -1):
            while p >= 0 and word1[p] != word2[j]:
                p -= 1
            last[j] = p
            if p >= 0:
                p -= 1

        ans = []
        j = 0
        changed = False

        for i in range(n):
            if j == m:
                break
                
            # Case 1: Characters match
            if word1[i] == word2[j]:
                ans.append(i)
                j += 1
            # Case 2: Characters don't match, but we can change word1[i]
            elif not changed and last[j + 1] > i:
                ans.append(i)
                j += 1
                changed = True

        return ans if len(ans) == m else []