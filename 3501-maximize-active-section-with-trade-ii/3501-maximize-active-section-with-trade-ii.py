import bisect

class SparseMin:
    def __init__(self, arr):
        n = len(arr)
        self.arr = arr
        if n == 0:
            self.log = []
            self.table = []
            return
        LOG = [0] * (n + 1)
        for i in range(2, n + 1):
            LOG[i] = LOG[i // 2] + 1
        table = [arr[:]]
        j = 1
        while (1 << j) <= n:
            prev = table[-1]
            half = 1 << (j - 1)
            cur = [
                min(prev[i], prev[i + half]) for i in range(n - (1 << j) + 1)
            ]
            table.append(cur)
            j += 1
        self.table = table
        self.LOG = LOG

    def query(self, l, r):
        if l > r:
            return float("inf")
        j = self.LOG[r - l + 1]
        return min(self.table[j][l], self.table[j][r - (1 << j) + 1])


class SparseMax:
    def __init__(self, arr):
        n = len(arr)
        self.arr = arr
        if n == 0:
            self.log = []
            self.table = []
            return
        LOG = [0] * (n + 1)
        for i in range(2, n + 1):
            LOG[i] = LOG[i // 2] + 1
        table = [arr[:]]
        j = 1
        while (1 << j) <= n:
            prev = table[-1]
            half = 1 << (j - 1)
            cur = [
                max(prev[i], prev[i + half]) for i in range(n - (1 << j) + 1)
            ]
            table.append(cur)
            j += 1
        self.table = table
        self.LOG = LOG

    def query(self, l, r):
        if l > r:
            return float("-inf")
        j = self.LOG[r - l + 1]
        return max(self.table[j][l], self.table[j][r - (1 << j) + 1])


class Solution:
    def maxActiveSectionsAfterTrade(self, s, queries):
        n = len(s)
        total_ones = s.count("1")

        # decompose into runs
        run_type = []
        run_start = []
        run_end = []
        run_len = []
        i = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            run_type.append(s[i])
            run_start.append(i)
            run_end.append(j - 1)
            run_len.append(j - i)
            i = j
        num_runs = len(run_type)

        run_id = [0] * n
        for ri in range(num_runs):
            for p in range(run_start[ri], run_end[ri] + 1):
                run_id[p] = ri

        one_run_indices = []
        L = []
        adjSum = []
        zero_run_indices = []
        Z = []

        for ri in range(num_runs):
            if run_type[ri] == "1":
                one_run_indices.append(ri)
                L.append(run_len[ri])
                left_len = run_len[ri - 1] if ri - 1 >= 0 else 0
                right_len = run_len[ri + 1] if ri + 1 < num_runs else 0
                adjSum.append(left_len + right_len)
            else:
                zero_run_indices.append(ri)
                Z.append(run_len[ri])

        spMinL = SparseMin(L)
        spMaxAdj = SparseMax(adjSum)
        spMaxZ = SparseMax(Z)

        def clipped_adj(k, idx_l, idx_r, l, r):
            ri = one_run_indices[k]

            if ri - 1 >= 0:
                if ri - 1 == idx_l:
                    left_len = run_end[idx_l] - l + 1
                else:
                    left_len = run_len[ri - 1]
            else:
                left_len = 0

            if ri + 1 < num_runs:
                if ri + 1 == idx_r:
                    right_len = r - run_start[idx_r] + 1
                else:
                    right_len = run_len[ri + 1]
            else:
                right_len = 0

            return left_len + right_len

        ans = []
        for l, r in queries:
            idx_l = run_id[l]
            idx_r = run_id[r]
            if idx_l == idx_r:
                ans.append(total_ones)
                continue

            k_lo = bisect.bisect_right(one_run_indices, idx_l)
            k_hi = bisect.bisect_left(one_run_indices, idx_r) - 1

            if k_lo > k_hi:
                ans.append(total_ones)
                continue

            minOne = spMinL.query(k_lo, k_hi)

            if k_lo == k_hi:
                adjMax = clipped_adj(k_lo, idx_l, idx_r, l, r)
            else:
                mid_lo, mid_hi = k_lo + 1, k_hi - 1
                mid_max = (
                    spMaxAdj.query(mid_lo, mid_hi)
                    if mid_lo <= mid_hi
                    else float("-inf")
                )
                adjMax = max(
                    clipped_adj(k_lo, idx_l, idx_r, l, r),
                    clipped_adj(k_hi, idx_l, idx_r, l, r),
                    mid_max,
                )

            candidates = []
            if run_type[idx_l] == "0":
                candidates.append(run_end[idx_l] - l + 1)
            if run_type[idx_r] == "0":
                candidates.append(r - run_start[idx_r] + 1)
            z_lo = bisect.bisect_right(zero_run_indices, idx_l)
            z_hi = bisect.bisect_left(zero_run_indices, idx_r) - 1
            if z_lo <= z_hi:
                candidates.append(spMaxZ.query(z_lo, z_hi))
            maxZeroGlobal = max(candidates) if candidates else 0

            netGain = max(maxZeroGlobal - minOne, adjMax)
            ans.append(total_ones + netGain)

        return ans