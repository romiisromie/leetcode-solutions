import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int[] ans = new int[m];
        int[] cnt = new int[n + 1];
        int l = 0, r = 0, active = 0;

        for (int i = 0; i < m; i++) {
            int rightTime = sortedQueries[i][0];
            int leftTime = rightTime - x;
            int idx = sortedQueries[i][1];

            while (r < logs.length && logs[r][1] <= rightTime) {
                if (cnt[logs[r][0]]++ == 0) {
                    active++;
                }
                r++;
            }
            while (l < logs.length && logs[l][1] < leftTime) {
                if (--cnt[logs[l][0]] == 0) {
                    active--;
                }
                l++;
            }
            ans[idx] = n - active;
        }
        return ans;
    }
}