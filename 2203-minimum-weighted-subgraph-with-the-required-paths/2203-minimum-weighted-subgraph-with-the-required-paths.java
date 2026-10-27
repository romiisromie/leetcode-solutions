import java.util.*;

public class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<List<long[]>> adj = new ArrayList<>(n);
        List<List<long[]>> revAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            long w = edge[2];
            adj.get(u).add(new long[]{v, w});
            revAdj.get(v).add(new long[]{u, w});
        }

        long[] distSrc1 = dijkstra(n, adj, src1);
        long[] distSrc2 = dijkstra(n, adj, src2);
        long[] distDest = dijkstra(n, revAdj, dest);

        long minWeight = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (distSrc1[i] != Long.MAX_VALUE && distSrc2[i] != Long.MAX_VALUE && distDest[i] != Long.MAX_VALUE) {
                long currentWeight = distSrc1[i] + distSrc2[i] + distDest[i];
                minWeight = Math.min(minWeight, currentWeight);
            }
        }

        return minWeight == Long.MAX_VALUE ? -1 : minWeight;
    }

    private long[] dijkstra(int n, List<List<long[]>> graph, int start) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{start, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];

            if (d > dist[u]) {
                continue;
            }

            for (long[] edge : graph.get(u)) {
                int v = (int) edge[0];
                long w = edge[1];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }

        return dist;
    }
}