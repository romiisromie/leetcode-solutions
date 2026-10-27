import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int maxQuality = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<List<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            adj.get(u).add(new int[]{v, time});
            adj.get(v).add(new int[]{u, time});
        }

        int[] visited = new int[n];
        visited[0] = 1;

        dfs(0, 0, values[0], maxTime, visited, adj, values);
        return maxQuality;
    }

    private void dfs(int node, int currentTime, int currentQuality, int maxTime, 
                     int[] visited, List<List<int[]>> adj, int[] values) {
        
        if (node == 0) {
            maxQuality = Math.max(maxQuality, currentQuality);
        }

        for (int[] neighbor : adj.get(node)) {
            int nextNode = neighbor[0];
            int travelTime = neighbor[1];

            if (currentTime + travelTime <= maxTime) {
                int nextQuality = currentQuality;
                if (visited[nextNode] == 0) {
                    nextQuality += values[nextNode];
                }

                visited[nextNode]++;
                dfs(nextNode, currentTime + travelTime, nextQuality, maxTime, visited, adj, values);
                visited[nextNode]--;
            }
        }
    }
}