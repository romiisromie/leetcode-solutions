from bisect import bisect_left
from functools import lru_cache

class Solution:
    def maximumWeight(self, intervals: list[list[int]]) -> list[int]:
        n = len(intervals)
        # Store [l, r, weight, original_index]
        sorted_intervals = sorted(
            (l, r, w, i) for i, (l, r, w) in enumerate(intervals)
        )
        starts = [interval[0] for interval in sorted_intervals]

        # Find the next valid interval for each interval
        next_pos = []
        for l, r, w, i in sorted_intervals:
            next_pos.append(bisect_left(starts, r + 1))

        @lru_cache(None)
        def solve(idx: int, count: int):
            if idx == n or count == 4:
                return (0, ())

            # Option 1: Skip the current interval
            best_weight, best_indices = solve(idx + 1, count)

            # Option 2: Take the current interval
            l, r, w, orig_idx = sorted_intervals[idx]
            next_w, next_indices = solve(next_pos[idx], count + 1)
            
            take_weight = w + next_w
            # Sort the indices to compare lexicographically
            take_indices = tuple(sorted((orig_idx,) + next_indices))

            # Choose the option with higher weight, or lexicographically smaller indices on tie
            if take_weight > best_weight:
                best_weight, best_indices = take_weight, take_indices
            elif take_weight == best_weight and take_weight > 0:
                if not best_indices or take_indices < best_indices:
                    best_weight, best_indices = take_weight, take_indices

            return best_weight, best_indices

        return list(solve(0, 0)[1])