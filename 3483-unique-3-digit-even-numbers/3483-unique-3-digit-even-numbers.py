from collections import Counter

class Solution:
    def totalNumbers(self, digits: list[int]) -> int:
        digit_counts = Counter(digits)
        count = 0

        # All 3-digit even numbers range from 100 to 998
        for num in range(100, 1000, 2):
            d1 = num // 100         # Hundreds digit
            d2 = (num // 10) % 10   # Tens digit
            d3 = num % 10           # Units digit

            needed = Counter([d1, d2, d3])

            # Check if we have enough of each digit in the input array
            if all(digit_counts[d] >= req_count for d, req_count in needed.items()):
                count += 1

        return count