public class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = getCost(0, cost1, cost2, costBoth, need1, need2);
        ans = Math.min(ans, getCost(need1, cost1, cost2, costBoth, need1, need2));
        ans = Math.min(ans, getCost(need2, cost1, cost2, costBoth, need1, need2));
        return ans;
    }

    private long getCost(long x, long cost1, long cost2, long costBoth, long need1, long need2) {
        long c1 = Math.max(0, need1 - x) * cost1;
        long c2 = Math.max(0, need2 - x) * cost2;
        return x * costBoth + c1 + c2;
    }
}