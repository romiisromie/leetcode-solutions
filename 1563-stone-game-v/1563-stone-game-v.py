class Solution:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        n = len(stoneValue)
        
        # Compute prefix sums
        prefix = [0] * (n + 1)
        for i in range(n):
            prefix[i + 1] = prefix[i] + stoneValue[i]
            
        dp = [[0] * n for _ in range(n)]
        maxL = [[0] * n for _ in range(n)]
        maxR = [[0] * n for _ in range(n)]
        
        # Base case: length 1
        for i in range(n):
            maxL[i][i] = stoneValue[i]
            maxR[i][i] = stoneValue[i]
            
        # DP over length L from 2 to n
        for length in range(2, n + 1):
            m = 0
            for i in range(n - length + 1):
                j = i + length - 1
                
                # Advance m to find the largest index where left_sum <= right_sum
                # total_sum = prefix[j + 1] - prefix[i]
                while m < j and 2 * (prefix[m + 1] - prefix[i]) <= (prefix[j + 1] - prefix[i]):
                    m += 1
                
                # m is now the first index where left_sum > right_sum
                # So for k in [i, m - 1], left_sum <= right_sum
                mid = m - 1
                res = 0
                
                if mid >= i:
                    left_sum = prefix[mid + 1] - prefix[i]
                    right_sum = prefix[j + 1] - prefix[mid + 1]
                    
                    if left_sum == right_sum:
                        # Case: left_sum == right_sum
                        res = max(res, left_sum + max(dp[i][mid], dp[mid + 1][j]))
                        # Check remaining left range [i, mid - 1]
                        if mid - 1 >= i:
                            res = max(res, maxL[i][mid - 1])
                    else:
                        # Case: left_sum < right_sum for range [i, mid]
                        res = max(res, maxL[i][mid])
                        
                # Check right range where left_sum > right_sum: [mid + 1, j - 1]
                # corresponding to maxR starting at index mid + 2
                if mid + 2 <= j:
                    res = max(res, maxR[mid + 2][j])
                    
                dp[i][j] = res
                
                # Update helper tables
                sub_sum = prefix[j + 1] - prefix[i]
                maxL[i][j] = max(maxL[i][j - 1], sub_sum + dp[i][j])
                maxR[i][j] = max(maxR[i + 1][j], sub_sum + dp[i][j])
                
        return dp[0][n - 1]