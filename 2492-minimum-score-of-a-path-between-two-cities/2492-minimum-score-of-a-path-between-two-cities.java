public class Solution {
    public int minScore(int n, int[][] roads) {
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] road : roads) {
            int rootU = find(parent, road[0]);
            int rootV = find(parent, road[1]);
            if (rootU != rootV) {
                parent[rootU] = rootV;
            }
        }

        int targetRoot = find(parent, 1);
        int minScore = Integer.MAX_VALUE;

        for (int[] road : roads) {
            if (find(parent, road[0]) == targetRoot) {
                minScore = Math.min(minScore, road[2]);
            }
        }

        return minScore;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}