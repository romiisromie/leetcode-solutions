import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 100_003L;
    private static final long BASE2 = 100_019L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];
        p1[0] = 1;
        p2[0] = 1;

        for (int i = 0; i < n; i++) {
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
            h1[i + 1] = (h1[i] * BASE1 + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + nums[i]) % MOD2;
        }

        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (hasUniqueOfLength(mid, n, p1, p2, h1, h2)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueOfLength(int len, int n, long[] p1, long[] p2, long[] h1, long[] h2) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int i = 0; i + len <= n; i++) {
            long val1 = (h1[i + len] - (h1[i] * p1[len]) % MOD1 + MOD1) % MOD1;
            long val2 = (h2[i + len] - (h2[i] * p2[len]) % MOD2 + MOD2) % MOD2;
            long key = (val1 << 32) ^ val2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}