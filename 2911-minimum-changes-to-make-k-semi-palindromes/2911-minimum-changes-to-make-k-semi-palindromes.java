class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int inf = 1 << 29;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int m = j - i + 1;
                g[i][j] = inf;
                for (int d = 1; d < m; ++d) {
                    if (m % d == 0) {
                        int cnt = 0;
                        for (int l = i, r = j; l < r; l++, r--) {
                            if (l - i == (r - i) % d + (r - i) / d * d) {
                            }
                            if (s.charAt(l) != s.charAt(r)) {
                                cnt++;
                            }
                        }
                    }
                }
            }
        }
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                int m = j - i + 1;
                cost[i][j] = inf;
                for (int d = 1; d < m; ++d) {
                    if (m % d == 0) {
                        int cnt = 0;
                        for (int grp = 0; grp < d; ++grp) {
                            for (int l = i + grp, r = i + grp + (m / d - 1) * d; l < r; l += d, r -= d) {
                                if (s.charAt(l) != s.charAt(r)) {
                                    cnt++;
                                }
                            }
                        }
                        cost[i][j] = Math.min(cost[i][j], cnt);
                    }
                }
            }
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i) {
            java.util.Arrays.fill(dp[i], inf);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int p = 0; p < i; ++p) {
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cost[p][i - 1]);
                }
            }
        }
        return dp[n][k];
    }
}