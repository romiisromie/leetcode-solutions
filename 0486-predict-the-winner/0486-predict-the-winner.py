class Solution:
    def predictTheWinner(self, nums: List[int]) -> bool:
        memo = {}

        def get_max_diff(i: int, j: int) -> int:
            if i == j:
                return nums[i]
            
            if (i, j) in memo:
                return memo[(i, j)]
            
            # Take left OR take right, minus the opponent's optimal advantage next turn
            take_left = nums[i] - get_max_diff(i + 1, j)
            take_right = nums[j] - get_max_diff(i, j - 1)
            
            memo[(i, j)] = max(take_left, take_right)
            return memo[(i, j)]

        return get_max_diff(0, len(nums) - 1) >= 0
        