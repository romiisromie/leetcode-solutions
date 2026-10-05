class Solution:
    def checkValidString(self, s: str) -> bool:
        min_open = 0
        max_open = 0
        
        for char in s:
            if char == '(':
                min_open += 1
                max_open += 1
            elif char == ')':
                min_open -= 1
                max_open -= 1
            else:  # char == '*'
                min_open -= 1
                max_open += 1
                
            # Too many closing parentheses
            if max_open < 0:
                return False
                
            # min_open cannot drop below 0
            min_open = max(min_open, 0)
            
        return min_open == 0