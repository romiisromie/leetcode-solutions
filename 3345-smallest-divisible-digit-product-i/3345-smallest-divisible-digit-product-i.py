class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        curr = n
        
        while True:
            # Calculate the product of digits of curr
            product = 1
            temp = curr
            while temp > 0:
                product *= (temp % 10)
                temp //= 10
            
            # Check if the product is divisible by t
            if product % t == 0:
                return curr
            
            curr += 1