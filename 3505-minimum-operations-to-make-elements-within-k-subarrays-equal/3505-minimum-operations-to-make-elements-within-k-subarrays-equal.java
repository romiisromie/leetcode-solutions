import java.util.TreeMap;
import java.util.Arrays;

class Solution {
    private static class BalancedSlidingWindow {
        private final TreeMap<Integer, Integer> lower = new TreeMap<>();
        private final TreeMap<Integer, Integer> upper = new TreeMap<>();
        private int lowerSize = 0;
        private int upperSize = 0;
        private long lowerSum = 0;
        private long upperSum = 0;
        private final int x;

        public BalancedSlidingWindow(int x) {
            this.x = x;
        }

        public void add(int val) {
            if (lowerSize == 0 || val <= lower.lastKey()) {
                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSum += val;
                lowerSize++;
            } else {
                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSum += val;
                upperSize++;
            }
            balance();
        }

        public void remove(int val) {
            if (lower.containsKey(val)) {
                int count = lower.get(val);
                if (count == 1) {
                    lower.remove(val);
                } else {
                    lower.put(val, count - 1);
                }
                lowerSum -= val;
                lowerSize--;
            } else {
                int count = upper.get(val);
                if (count == 1) {
                    upper.remove(val);
                } else {
                    upper.put(val, count - 1);
                }
                upperSum -= val;
                upperSize--;
            }
            balance();
        }

        private void balance() {
            int targetLowerSize = (x + 1) / 2;
            while (lowerSize < targetLowerSize && upperSize > 0) {
                int firstKey = upper.firstKey();
                int count = upper.get(firstKey);
                if (count == 1) upper.remove(firstKey);
                else upper.put(firstKey, count - 1);
                upperSum -= firstKey;
                upperSize--;

                lower.put(firstKey, lower.getOrDefault(firstKey, 0) + 1);
                lowerSum += firstKey;
                lowerSize++;
            }
            while (lowerSize > targetLowerSize) {
                int lastKey = lower.lastKey();
                int count = lower.get(lastKey);
                if (count == 1) lower.remove(lastKey);
                else lower.put(lastKey, count - 1);
                lowerSum -= lastKey;
                lowerSize--;

                upper.put(lastKey, upper.getOrDefault(lastKey, 0) + 1);
                upperSum += lastKey;
                upperSize++;
            }
        }

        public long getCost() {
            long median = lower.lastKey();
            return (median * lowerSize - lowerSum) + (upperSum - median * upperSize);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        BalancedSlidingWindow window = new BalancedSlidingWindow(x);

        for (int i = 0; i < n; i++) {
            window.add(nums[i]);
            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
                window.remove(nums[i - x + 1]);
            }
        }

        long[][] dp = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        for (int i = 1; i <= k; i++) {
            for (int j = i * x; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];
                long option = dp[i - 1][j - x] + cost[j - x];
                if (option < dp[i][j]) {
                    dp[i][j] = option;
                }
            }
        }

        return dp[k][n];
    }
}