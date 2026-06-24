import heapq

class Solution(object):
    def minimumCost(self, nums, k, dist):
        """
        :type nums: List[int]
        :type k: int
        :type dist: int
        :rtype: int
        """
        n = len(nums)
        m = k - 1
        
        small = [] 
        large = []
        del_small = {}
        del_large = {}
        
        self.small_sum = 0
        self.small_cnt = 0
        
        def add_to_small(val):
            heapq.heappush(small, -val)
            self.small_sum += val
            self.small_cnt += 1
            
        def add_to_large(val):
            heapq.heappush(large, val)
            
        def clean_heaps():
            while small and del_small.get(-small[0], 0) > 0:
                val = -heapq.heappop(small)
                del_small[val] -= 1
            while large and del_large.get(large[0], 0) > 0:
                val = heapq.heappop(large)
                del_large[val] -= 1
                
        def balance():
            clean_heaps()
            while self.small_cnt > m:
                val = -heapq.heappop(small)
                self.small_sum -= val
                self.small_cnt -= 1
                add_to_large(val)
                clean_heaps()
            while self.small_cnt < m and large:
                val = heapq.heappop(large)
                add_to_small(val)
                clean_heaps()
                
        def remove_val(val):
            clean_heaps()
            if small and val <= -small[0]:
                del_small[val] = del_small.get(val, 0) + 1
                self.small_sum -= val
                self.small_cnt -= 1
            else:
                del_large[val] = del_large.get(val, 0) + 1
            balance()

        for i in range(1, dist + 2):
            add_to_small(nums[i])
        balance()
        
        min_sum = self.small_sum
        
        for i in range(2, n - dist):
            if i + dist >= n:
                break
            remove_val(nums[i - 1])
            clean_heaps()
            if small and nums[i + dist] <= -small[0]:
                add_to_small(nums[i + dist])
            else:
                add_to_large(nums[i + dist])
            balance()
            min_sum = min(min_sum, self.small_sum)
            
        return nums[0] + min_sum