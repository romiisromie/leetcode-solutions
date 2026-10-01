class Solution:
    def hasValidPath(self, grid: list[list[str]]) -> bool:
        m, n = len(grid), len(grid[0])

        # A valid parentheses string must have an even length
        if (m + n - 1) % 2 != 0:
            return False

        # Path must start with '(' and end with ')'
        if grid[0][0] == ')' or grid[m - 1][n - 1] == '(':
            return False

        # visited[r][c] stores a set of possible balance scores at grid[r][c]
        visited = [[set() for _ in range(n)] for _ in range(m)]
        
        # Initial state at (0, 0)
        visited[0][0].add(1)

        for r in range(m):
            for c in range(n):
                for bal in visited[r][c]:
                    # Explore right neighbor
                    if c + 1 < n:
                        next_bal = bal + (1 if grid[r][c + 1] == '(' else -1)
                        remaining_steps = (m - 1 - r) + (n - 1 - (c + 1))
                        if next_bal >= 0 and next_bal <= remaining_steps:
                            visited[r][c + 1].add(next_bal)

                    # Explore down neighbor
                    if r + 1 < m:
                        next_bal = bal + (1 if grid[r + 1][c] == '(' else -1)
                        remaining_steps = (m - 1 - (r + 1)) + (n - 1 - c)
                        if next_bal >= 0 and next_bal <= remaining_steps:
                            visited[r + 1][c].add(next_bal)

        # A valid path must end with a balance score of 0
        return 0 in visited[m - 1][n - 1]
        