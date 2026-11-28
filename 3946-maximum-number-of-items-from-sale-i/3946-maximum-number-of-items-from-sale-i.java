import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] cnt = new int[1501];
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            cnt[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] multCount = new int[1501];
        for (int d = 1; d <= 1500; d++) {
            int total = 0;
            for (int m = d; m <= 1500; m += d) {
                total += cnt[m];
            }
            multCount[d] = total - 1;
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int[] item : items) {
            int price = item[1];
            int val = 1 + multCount[item[0]];
            for (int b = budget; b >= price; b--) {
                if (dp[b - price] != -1) {
                    dp[b] = Math.max(dp[b], dp[b - price] + val);
                }
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            if (dp[b] != -1) {
                int rem = budget - b;
                ans = Math.max(ans, dp[b] + rem / minPrice);
            }
        }

        return ans;
    }
}