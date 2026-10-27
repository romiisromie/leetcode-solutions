import java.util.*;

public class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int q = queries.length;
        int[] ans = new int[q];
        Arrays.fill(ans, -1);

        List<List<int[]>> deferred = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            deferred.add(new ArrayList<>());
        }

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            if (a == b) {
                ans[i] = a;
            } else if (a < b && heights[a] < heights[b]) {
                ans[i] = b;
            } else if (a > b && heights[a] > heights[b]) {
                ans[i] = a;
            } else {
                int maxIdx = Math.max(a, b);
                int maxH = Math.max(heights[a], heights[b]);
                deferred.get(maxIdx).add(new int[]{maxH, i});
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[0]));

        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] < heights[i]) {
                ans[pq.poll()[1]] = i;
            }
            for (int[] query : deferred.get(i)) {
                pq.offer(query);
            }
        }

        return ans;
    }
}