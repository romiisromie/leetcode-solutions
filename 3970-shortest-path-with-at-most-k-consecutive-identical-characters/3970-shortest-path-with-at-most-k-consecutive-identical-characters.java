import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (d > dist[u][count]) continue;
            if (u == n - 1) return d;

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int w = edge[1];
                char nextChar = labels.charAt(v);
                char currChar = labels.charAt(u);

                int nextCount = (nextChar == currChar) ? count + 1 : 1;
                if (nextCount > k) continue;

                if (d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{dist[v][nextCount], v, nextCount});
                }
            }
        }

        return -1;
    }
}