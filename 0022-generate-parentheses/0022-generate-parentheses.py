class Solution:
    def generateParenthesis(self, n: int) -> list[str]:
        result = []

        def backtrack(current_str: str, left: int, right: int):
            if len(current_str) == 2 * n:
                result.append(current_str)
                return

            if left < n:
                backtrack(current_str + "(", left + 1, right)
            
            if right < left:
                backtrack(current_str + ")", left, right + 1)

        backtrack("", 0, 0)
        return result