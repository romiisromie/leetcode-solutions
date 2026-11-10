class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            int minCost = Integer.MAX_VALUE;
            
            if (i >= 1) {
                minCost = Math.min(minCost, dp[i - 1] + costs[i - 1] + 1);
            }
            if (i >= 2) {
                minCost = Math.min(minCost, dp[i - 2] + costs[i - 1] + 4);
            }
            if (i >= 3) {
                minCost = Math.min(minCost, dp[i - 3] + costs[i - 1] + 9);
            }
            
            dp[i] = minCost;
        }
        
        return dp[n];
    }
}