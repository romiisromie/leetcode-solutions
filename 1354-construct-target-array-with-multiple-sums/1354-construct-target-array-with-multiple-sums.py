import heapq

class Solution(object):
    def isPossible(self, target):
        if len(target) == 1:
            return target[0] == 1
            
        total_sum = sum(target)
        heap = [-x for x in target]
        heapq.heapify(heap)
        
        while True:
            max_val = -heapq.heappop(heap)
            rest_sum = total_sum - max_val
            
            if max_val == 1 or rest_sum == 1:
                return True
                
            if max_val < rest_sum or rest_sum == 0:
                return False
                
            prev_val = max_val % rest_sum
            
            if prev_val == 0:
                return False
                
            total_sum = rest_sum + prev_val
            heapq.heappush(heap, -prev_val)