import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        Set<Integer>[][] dp = new Set[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        
        dp[0][0].add(grid[0][0]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                int val = grid[i][j];
                
                if (i > 0) {
                    for (int xor : dp[i - 1][j]) {
                        dp[i][j].add(xor ^ val);
                    }
                }
                
                if (j > 0) {
                    for (int xor : dp[i][j - 1]) {
                        dp[i][j].add(xor ^ val);
                    }
                }
            }
        }
        
        int minXor = Integer.MAX_VALUE;
        for (int xor : dp[m - 1][n - 1]) {
            if (xor < minXor) {
                minXor = xor;
            }
        }
        
        return minXor;
    }
}