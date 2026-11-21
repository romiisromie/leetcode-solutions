import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> origTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            origTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origTree.get(parent[i]).add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] lastCharNode = new int[26];
        Arrays.fill(lastCharNode, -1);

        dfsFindParent(0, origTree, s, lastCharNode, newParent, parent);

        List<List<Integer>> newTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            newTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newTree.get(newParent[i]).add(i);
        }

        int[] ans = new int[n];
        dfsSize(0, newTree, ans);
        return ans;
    }

    private void dfsFindParent(int u, List<List<Integer>> origTree, String s, int[] lastCharNode, int[] newParent, int[] origParent) {
        int c = s.charAt(u) - 'a';
        int prev = lastCharNode[c];
        if (u != 0) {
            newParent[u] = (prev != -1) ? prev : origParent[u];
        }
        lastCharNode[c] = u;
        for (int v : origTree.get(u)) {
            dfsFindParent(v, origTree, s, lastCharNode, newParent, origParent);
        }
        lastCharNode[c] = prev;
    }

    private int dfsSize(int u, List<List<Integer>> newTree, int[] ans) {
        int size = 1;
        for (int v : newTree.get(u)) {
            size += dfsSize(v, newTree, ans);
        }
        ans[u] = size;
        return size;
    }
}