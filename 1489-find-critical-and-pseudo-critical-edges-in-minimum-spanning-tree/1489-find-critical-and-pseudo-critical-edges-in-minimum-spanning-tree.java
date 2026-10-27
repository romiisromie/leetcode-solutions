import java.util.*;

public class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            newEdges[i][0] = edges[i][0];
            newEdges[i][1] = edges[i][1];
            newEdges[i][2] = edges[i][2];
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, Comparator.comparingInt(a -> a[2]));

        int mstWeight = buildMST(n, newEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int weightWithout = buildMST(n, newEdges, i, -1);
            if (weightWithout > mstWeight) {
                critical.add(newEdges[i][3]);
            } else {
                int weightWith = buildMST(n, newEdges, -1, i);
                if (weightWith == mstWeight) {
                    pseudoCritical.add(newEdges[i][3]);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    private int buildMST(int n, int[][] edges, int skipEdgeIdx, int forceEdgeIdx) {
        UnionFind uf = new UnionFind(n);
        int weight = 0;
        int edgesCount = 0;

        if (forceEdgeIdx != -1) {
            uf.union(edges[forceEdgeIdx][0], edges[forceEdgeIdx][1]);
            weight += edges[forceEdgeIdx][2];
            edgesCount++;
        }

        for (int i = 0; i < edges.length; i++) {
            if (i == skipEdgeIdx || i == forceEdgeIdx) {
                continue;
            }
            if (uf.union(edges[i][0], edges[i][1])) {
                weight += edges[i][2];
                edgesCount++;
            }
        }

        return edgesCount == n - 1 ? weight : Integer.MAX_VALUE;
    }

    private static class UnionFind {
        private final int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }
}