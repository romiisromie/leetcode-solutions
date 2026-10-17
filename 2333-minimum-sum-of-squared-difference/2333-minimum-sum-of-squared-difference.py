class Solution:
    def minSumSquareDiff(self, nums1: list[int], nums2: list[int], k1: int, k2: int) -> int:
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        total_k = k1 + k2
        
        # If total budget can cover all differences, we can reduce everything to 0
        if sum(diff) <= total_k:
            return 0
            
        # Frequency array to count occurrences of each difference
        max_val = max(diff)
        count = [0] * (max_val + 1)
        for d in diff:
            count[d] += 1
            
        # Greedily reduce the largest differences
        for i in range(max_val, 0, -1):
            if count[i] > 0:
                reduction = min(total_k, count[i])
                count[i] -= reduction
                count[i - 1] += reduction
                total_k -= reduction
                if total_k == 0:
                    break
                    
        # Calculate the final sum of squared differences
        ans = 0
        for i in range(1, max_val + 1):
            if count[i] > 0:
                ans += count[i] * (i * i)
                
        return ans