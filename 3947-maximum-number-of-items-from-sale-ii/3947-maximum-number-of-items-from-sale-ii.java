import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n + 1];
        long minPrice = Long.MAX_VALUE;
        for (int[] item : items) {
            cnt[item[0]]++;
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] multCount = new int[n + 1];
        for (int d = 1; d <= n; d++) {
            int total = 0;
            for (int m = d; m <= n; m += d) {
                total += cnt[m];
            }
            multCount[d] = total - 1;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long ans = budget / minPrice;
        long spent = 0;
        long k = 0;
        for (int[] item : items) {
            long price = item[1];
            long s = multCount[item[0]];
            if (s <= 0) {
                continue;
            }
            long canBuy = (budget - spent) / price;
            if (canBuy <= 0) {
                break;
            }
            long take = Math.min(s, canBuy);
            spent += take * price;
            k += take;
            long cur = 2 * k + (budget - spent) / minPrice;
            if (cur > ans) {
                ans = cur;
            }
        }

        return (int) ans;
    }
}