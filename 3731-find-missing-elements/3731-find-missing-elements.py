class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        min_num = min(nums)
        max_num = max(nums)
        num_set = set(nums)
        
        missing = []
        for x in range(min_num, max_num + 1):
            if x not in num_set:
                missing.append(x)
                
        return missing