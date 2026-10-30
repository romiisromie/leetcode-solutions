import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int[][] maxST = new int[17][n];
        int[][] minST = new int[17][n];

        for (int i = 0; i < n; i++) {
            maxST[0][i] = nums[i];
            minST[0][i] = nums[i];
        }

        for (int j = 1; j < 17; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                maxST[j][i] = Math.max(maxST[j - 1][i], maxST[j - 1][i + (1 << (j - 1))]);
                minST[j][i] = Math.min(minST[j - 1][i], minST[j - 1][i + (1 << (j - 1))]);
            }
        }

        int[] log = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int i = 0; i < n; i++) {
            long val = (long) queryMax(i, n - 1, maxST, log) - queryMin(i, n - 1, minST, log);
            pq.offer(new long[]{val, i, n - 1});
        }

        long totalValue = 0;
        for (int i = 0; i < k; i++) {
            long[] curr = pq.poll();
            totalValue += curr[0];
            int l = (int) curr[1];
            int r = (int) curr[2];

            if (r - 1 >= l) {
                long nextVal = (long) queryMax(l, r - 1, maxST, log) - queryMin(l, r - 1, minST, log);
                pq.offer(new long[]{nextVal, l, r - 1});
            }
        }

        return totalValue;
    }

    private int queryMax(int l, int r, int[][] maxST, int[] log) {
        int len = r - l + 1;
        int j = log[len];
        return Math.max(maxST[j][l], maxST[j][r - (1 << j) + 1]);
    }

    private int queryMin(int l, int r, int[][] minST, int[] log) {
        int len = r - l + 1;
        int j = log[len];
        return Math.min(minST[j][l], minST[j][r - (1 << j) + 1]);
    }
}