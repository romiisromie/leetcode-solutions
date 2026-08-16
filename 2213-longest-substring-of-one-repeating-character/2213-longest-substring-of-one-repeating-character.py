class SegmentTree:
    def __init__(self, s: str):
        self.n = len(s)
        self.tree_size = 4 * self.n
        self.pref_char = [''] * self.tree_size
        self.pref_len = [0] * self.tree_size
        self.suff_char = [''] * self.tree_size
        self.suff_len = [0] * self.tree_size
        self.max_len = [0] * self.tree_size
        self.build(s, 1, 0, self.n - 1)

    def _merge(self, node: int, l: int, r: int, mid: int):
        left, right = 2 * node, 2 * node + 1
        len_l = mid - l + 1
        len_r = r - mid

        self.pref_char[node] = self.pref_char[left]
        self.pref_len[node] = self.pref_len[left]
        if self.pref_len[left] == len_l and self.pref_char[left] == self.pref_char[right]:
            self.pref_len[node] += self.pref_len[right]

        self.suff_char[node] = self.suff_char[right]
        self.suff_len[node] = self.suff_len[right]
        if self.suff_len[right] == len_r and self.suff_char[right] == self.suff_char[left]:
            self.suff_len[node] += self.suff_len[left]

        self.max_len[node] = max(self.max_len[left], self.max_len[right])
        if self.suff_char[left] == self.pref_char[right]:
            self.max_len[node] = max(self.max_len[node], self.suff_len[left] + self.pref_len[right])

    def build(self, s: str, node: int, l: int, r: int):
        if l == r:
            self.pref_char[node] = self.suff_char[node] = s[l]
            self.pref_len[node] = self.suff_len[node] = self.max_len[node] = 1
            return
        
        mid = (l + r) // 2
        self.build(s, 2 * node, l, mid)
        self.build(s, 2 * node + 1, mid + 1, r)
        self._merge(node, l, r, mid)

    def update(self, node: int, l: int, r: int, idx: int, char: str):
        if l == r:
            self.pref_char[node] = self.suff_char[node] = char
            self.pref_len[node] = self.suff_len[node] = self.max_len[node] = 1
            return
        
        mid = (l + r) // 2
        if idx <= mid:
            self.update(2 * node, l, mid, idx, char)
        else:
            self.update(2 * node + 1, mid + 1, r, idx, char)
            
        self._merge(node, l, r, mid)


class Solution:
    def longestRepeating(self, s: str, queryCharacters: str, queryIndices: List[int]) -> List[int]:
        tree = SegmentTree(s)
        ans = []
        
        for char, idx in zip(queryCharacters, queryIndices):
            tree.update(1, 0, len(s) - 1, idx, char)
            ans.append(tree.max_len[1])
            
        return ans