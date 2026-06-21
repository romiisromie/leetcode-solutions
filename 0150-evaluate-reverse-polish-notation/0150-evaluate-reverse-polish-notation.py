class Solution(object):
    def evalRPN(self, tokens):
        stack = []
        operators = set(["+", "-", "*", "/"])
        
        for token in tokens:
            if token not in operators:
                stack.append(int(token))
            else:
                b = stack.pop()
                a = stack.pop()
                
                if token == "+":
                    stack.append(a + b)
                elif token == "-":
                    stack.append(a - b)
                elif token == "*":
                    stack.append(a * b)
                elif token == "/":
                    stack.append(int(float(a) / b))
                    
        return stack[0]