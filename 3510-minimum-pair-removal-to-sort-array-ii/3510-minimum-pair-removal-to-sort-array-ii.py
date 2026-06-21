import heapq

class Solution(object):
    def minimumPairRemoval(self, nums):
        n = len(nums)
        if n < 2:
            return 0
            
        arr = [int(x) for x in nums]
        left_idx = [i - 1 for i in range(n)]
        right_idx = [i + 1 for i in range(n)]
        
        violations = 0
        for i in range(n - 1):
            if arr[i] > arr[i + 1]:
                violations += 1
                
        heap = []
        for i in range(n - 1):
            heapq.heappush(heap, (arr[i] + arr[i + 1], i, i, i + 1))
            
        operations = 0
        valid_version = {}
        for i in range(n - 1):
            valid_version[(i, i + 1)] = arr[i] + arr[i + 1]
            
        while violations > 0 and heap:
            pair_sum, start_pos, l, r = heapq.heappop(heap)
            
            if (l, r) not in valid_version or valid_version[(l, r)] != pair_sum:
                continue
                
            pl = left_idx[l]
            nr = right_idx[r]
            
            if arr[l] > arr[r]:
                violations -= 1
            if pl >= 0 and arr[pl] > arr[l]:
                violations -= 1
            if nr < n and arr[r] > arr[nr]:
                violations -= 1
                
            del valid_version[(l, r)]
            if pl >= 0:
                del valid_version[(pl, l)]
            if nr < n:
                del valid_version[(r, nr)]
                
            arr[l] += arr[r]
            right_idx[l] = nr
            if nr < n:
                left_idx[nr] = l
                
            if pl >= 0 and arr[pl] > arr[l]:
                violations += 1
            if nr < n and arr[l] > arr[nr]:
                violations += 1
                
            if pl >= 0:
                new_sum = arr[pl] + arr[l]
                valid_version[(pl, l)] = new_sum
                heapq.heappush(heap, (new_sum, pl, pl, l))
            if nr < n:
                new_sum = arr[l] + arr[nr]
                valid_version[(l, nr)] = new_sum
                heapq.heappush(heap, (new_sum, l, l, nr))
                
            operations += 1
            
        return operations