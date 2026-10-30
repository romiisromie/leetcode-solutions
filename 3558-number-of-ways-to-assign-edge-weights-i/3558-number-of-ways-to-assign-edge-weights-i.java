import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int maxDepth = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int d = curr[1];
            maxDepth = Math.max(maxDepth, d);

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new int[]{v, d + 1});
                }
            }
        }

        long ans = 1;
        long base = 2;
        int exp = maxDepth - 1;
        long mod = 1000000007;

        while (exp > 0) {
            if (exp % 2 == 1) {
                ans = (ans * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }

        return maxDepth == 0 ? 0 : (int) ans;
    }
}