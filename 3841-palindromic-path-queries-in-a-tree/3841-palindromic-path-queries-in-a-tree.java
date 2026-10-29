import java.util.*;

class Solution {
    private List<List<Integer>> adj;
    private int[] inTime;
    private int[] outTime;
    private int timer;
    private int[] depth;
    private int[][] up;
    private int LOG;
    private int[] nodeMask;
    private int[] tree;
    private int[] lazy;
    private char[] sArr;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        LOG = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[n][LOG];
        inTime = new int[n];
        outTime = new int[n];
        depth = new int[n];
        timer = 0;

        dfs(0, -1, 0);

        sArr = s.toCharArray();
        nodeMask = new int[n];
        computeMasks(0, -1, 0);

        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int oldMask = 1 << (sArr[u] - 'a');
                int newMask = 1 << (c - 'a');
                int diff = oldMask ^ newMask;
                sArr[u] = c;
                updateRange(1, 0, n - 1, inTime[u], outTime[u], diff);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v);
                int maskU = queryPoint(1, 0, n - 1, inTime[u]);
                int maskV = queryPoint(1, 0, n - 1, inTime[v]);
                int maskLCA = 1 << (sArr[lca] - 'a');
                int pathMask = maskU ^ maskV ^ maskLCA;
                ans.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d) {
        inTime[u] = timer++;
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            if (up[u][i - 1] != -1) {
                up[u][i] = up[up[u][i - 1]][i - 1];
            } else {
                up[u][i] = -1;
            }
        }
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        outTime[u] = timer - 1;
    }

    private void computeMasks(int u, int p, int currentMask) {
        currentMask ^= (1 << (sArr[u] - 'a'));
        nodeMask[inTime[u]] = currentMask;
        for (int v : adj.get(u)) {
            if (v != p) {
                computeMasks(v, u, currentMask);
            }
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = nodeMask[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void pushLazy(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[2 * node] ^= lazy[node];
            lazy[2 * node] ^= lazy[node];
            tree[2 * node + 1] ^= lazy[node];
            lazy[2 * node + 1] ^= lazy[node];
            lazy[node] = 0;
        }
    }

    private void updateRange(int node, int start, int end, int l, int r, int val) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            tree[node] ^= val;
            lazy[node] ^= val;
            return;
        }
        pushLazy(node, start, end);
        int mid = (start + end) / 2;
        updateRange(2 * node, start, mid, l, r, val);
        updateRange(2 * node + 1, mid + 1, end, l, r, val);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryPoint(int node, int start, int end, int idx) {
        if (start == end) {
            return tree[node];
        }
        pushLazy(node, start, end);
        int mid = (start + end) / 2;
        if (idx <= mid) {
            return queryPoint(2 * node, start, mid, idx);
        } else {
            return queryPoint(2 * node + 1, mid + 1, end, idx);
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }
}