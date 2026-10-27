import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0);
        dfs(graph, 0, graph.length - 1, currentPath, paths);
        return paths;
    }

    private void dfs(int[][] graph, int node, int target, List<Integer> currentPath, List<List<Integer>> paths) {
        if (node == target) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }

        for (int neighbor : graph[node]) {
            currentPath.add(neighbor);
            dfs(graph, neighbor, target, currentPath, paths);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}