import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = new int[2];
                dfs(i, adj, visited, counts);
                
                int vertexCount = counts[0];
                int edgeCount = counts[1] / 2;

                if (edgeCount == (vertexCount * (vertexCount - 1)) / 2) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, int[] counts) {
        visited[node] = true;
        counts[0]++;
        counts[1] += adj.get(node).size();

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, counts);
            }
        }
    }
}