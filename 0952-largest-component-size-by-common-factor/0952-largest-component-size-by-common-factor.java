import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int largestComponentSize(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        DisjointSetUnion dsu = new DisjointSetUnion(maxVal + 1);

        for (int num : nums) {
            for (int factor = 2; factor * factor <= num; factor++) {
                if (num % factor == 0) {
                    dsu.union(num, factor);
                    dsu.union(num, num / factor);
                }
            }
        }

        Map<Integer, Integer> componentCounts = new HashMap<>();
        int maxComponentSize = 0;

        for (int num : nums) {
            int root = dsu.find(num);
            int count = componentCounts.getOrDefault(root, 0) + 1;
            componentCounts.put(root, count);
            maxComponentSize = Math.max(maxComponentSize, count);
        }

        return maxComponentSize;
    }

    private static class DisjointSetUnion {
        private final int[] parent;

        public DisjointSetUnion(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }
}