class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num)
        half = n // 2
        
        sum_left = sum(int(c) for c in num[:half] if c != '?')
        sum_right = sum(int(c) for c in num[half:] if c != '?')
        
        q_left = num[:half].count('?')
        q_right = num[half:].count('?')
        
        # Total number of '?' is odd -> Alice can always win
        if (q_left + q_right) % 2 != 0:
            return True
            
        # Bob wins iff the sum difference equals half the '?' difference multiplied by 9
        # (sum_left - sum_right) + (q_left - q_right) / 2 * 9 == 0
        diff_sum = sum_left - sum_right
        diff_q = q_left - q_right
        
        return diff_sum + (diff_q // 2) * 9 != 0