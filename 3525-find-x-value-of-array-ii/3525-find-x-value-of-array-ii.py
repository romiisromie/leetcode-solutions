class SegmentTree:
    def __init__(self, nums: list[int], k: int):
        self.n = len(nums)
        self.k = k
        self.tree_prod = [1] * (4 * self.n)
        self.tree_counts = [[0] * k for _ in range(4 * self.n)]
        self.build(nums, 0, 0, self.n - 1)

    def _merge(self, left_idx: int, right_idx: int, node: int):
        k = self.k
        left_prod = self.tree_prod[left_idx]
        right_prod = self.tree_prod[right_idx]
        
        self.tree_prod[node] = (left_prod * right_prod) % k
        
        # Combine prefix counts
        counts = list(self.tree_counts[left_idx])
        for r in range(k):
            c = self.tree_counts[right_idx][r]
            if c > 0:
                new_rem = (left_prod * r) % k
                counts[new_rem] += c
                
        self.tree_counts[node] = counts

    def build(self, nums: list[int], node: int, start: int, end: int):
        if start == end:
            val = nums[start] % self.k
            self.tree_prod[node] = val
            self.tree_counts[node][val] = 1
            return

        mid = (start + end) // 2
        left_node, right_node = 2 * node + 1, 2 * node + 2
        self.build(nums, left_node, start, mid)
        self.build(nums, right_node, mid + 1, end)
        self._merge(left_node, right_node, node)

    def update(self, node: int, start: int, end: int, idx: int, val: int):
        if start == end:
            v = val % self.k
            self.tree_prod[node] = v
            self.tree_counts[node] = [0] * self.k
            self.tree_counts[node][v] = 1
            return

        mid = (start + end) // 2
        left_node, right_node = 2 * node + 1, 2 * node + 2
        if idx <= mid:
            self.update(left_node, start, mid, idx, val)
        else:
            self.update(right_node, mid + 1, end, idx, val)
        
        self._merge(left_node, right_node, node)

    def query(self, node: int, start: int, end: int, ql: int, qr: int):
        if ql <= start and end <= qr:
            return self.tree_prod[node], list(self.tree_counts[node])

        mid = (start + end) // 2
        left_node, right_node = 2 * node + 1, 2 * node + 2

        if qr <= mid:
            return self.query(left_node, start, mid, ql, qr)
        if ql > mid:
            return self.query(right_node, mid + 1, end, ql, qr)

        left_prod, left_counts = self.query(left_node, start, mid, ql, qr)
        right_prod, right_counts = self.query(right_node, mid + 1, end, ql, qr)

        res_prod = (left_prod * right_prod) % self.k
        res_counts = list(left_counts)
        for r in range(self.k):
            c = right_counts[r]
            if c > 0:
                res_counts[(left_prod * r) % self.k] += c

        return res_prod, res_counts


class Solution:
    def resultArray(self, nums: list[int], k: int, queries: list[list[int]]) -> list[int]:
        st = SegmentTree(nums, k)
        ans = []
        n = len(nums)

        for idx, val, start_i, x_i in queries:
            # 1. Update element at index
            st.update(0, 0, n - 1, idx, val)

            # 2. Query range [start_i, n - 1]
            _, counts = st.query(0, 0, n - 1, start_i, n - 1)

            # 3. Append count for required remainder x_i
            ans.append(counts[x_i])

        return ans