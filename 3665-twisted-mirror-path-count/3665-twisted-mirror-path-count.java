class Solution {
    private int[][][] memo;
    private int m, n;
    private int[][] g;

    public int uniquePaths(int[][] grid) {
        this.g = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.memo = new int[m][n][2];
        
        long MOD = 1000000007;
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || dp[i][j] == 0) continue;

                int[] rightDest = getDest(i, j + 1, 0);
                if (rightDest[0] != -1) {
                    dp[rightDest[0]][rightDest[1]] = (dp[rightDest[0]][rightDest[1]] + dp[i][j]) % MOD;
                }

                int[] downDest = getDest(i + 1, j, 1);
                if (downDest[0] != -1) {
                    dp[downDest[0]][downDest[1]] = (dp[downDest[0]][downDest[1]] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int[] getDest(int r, int c, int dir) {
        if (r >= m || c >= n) return new int[]{-1, -1};
        if (g[r][c] == 0) return new int[]{r, c};
        if (memo[r][c][dir] != 0) {
            int val = memo[r][c][dir];
            if (val == -1) return new int[]{-1, -1};
            return new int[]{val / n, val % n};
        }

        int[] res;
        if (dir == 0) {
            res = getDest(r + 1, c, 1);
        } else {
            res = getDest(r, c + 1, 0);
        }

        if (res[0] == -1) {
            memo[r][c][dir] = -1;
        } else {
            memo[r][c][dir] = res[0] * n + res[1];
        }
        return res;
    }
}