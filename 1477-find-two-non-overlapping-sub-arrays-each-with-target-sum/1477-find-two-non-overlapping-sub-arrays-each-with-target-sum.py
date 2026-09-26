class Solution:
    def minSumOfLengths(self, arr: list[int], target: int) -> int:
        n = len(arr)
        INF = float('inf')
        
        # min_len[i] stores the minimum length of a valid subarray in arr[0...i]
        min_len = [INF] * n
        
        prefix_map = {0: -1}  # prefix_sum -> index
        curr_sum = 0
        min_total_len = INF
        best_so_far = INF

        for i, val in enumerate(arr):
            curr_sum += val
            
            # Check if there is a subarray ending at i with sum equal to target
            if (curr_sum - target) in prefix_map:
                prev_idx = prefix_map[curr_sum - target]
                curr_length = i - prev_idx
                
                # If a valid subarray exists before prev_idx, update min_total_len
                if prev_idx >= 0 and min_len[prev_idx] != INF:
                    min_total_len = min(min_total_len, min_len[prev_idx] + curr_length)
                
                best_so_far = min(best_so_far, curr_length)
            
            # Record current prefix sum
            prefix_map[curr_sum] = i
            # Update min_len for index i
            min_len[i] = best_so_far

        return min_total_len if min_total_len != INF else -1