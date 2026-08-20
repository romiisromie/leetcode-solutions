from collections import defaultdict
from typing import List

class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        # Map rows to their reserved seats (only considering seats 2 through 9)
        reserved = defaultdict(set)
        for row, seat in reservedSeats:
            if 2 <= seat <= 9:
                reserved[row].add(seat)
        
        # Unreserved rows can each seat 2 groups
        ans = (n - len(reserved)) * 2
        
        # Evaluate rows with reservations
        for row, seats in reserved.items():
            left_free = not (seats & {2, 3, 4, 5})
            right_free = not (seats & {6, 7, 8, 9})
            middle_free = not (seats & {4, 5, 6, 7})
            
            if left_free and right_free:
                ans += 2
            elif left_free or right_free or middle_free:
                ans += 1
                
        return ans