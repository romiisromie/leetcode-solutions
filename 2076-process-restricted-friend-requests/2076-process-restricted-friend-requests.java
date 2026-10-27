public class Solution {
    public boolean[] jsonRequests(int n, int[][] restrictions, int[][] requests) {
        return friendRequests(n, restrictions, requests);
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        boolean[] result = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0];
            int v = requests[i][1];

            int rootU = find(parent, u);
            int rootV = find(parent, v);

            if (rootU == rootV) {
                result[i] = true;
                continue;
            }

            boolean isValid = true;
            for (int[] restriction : restrictions) {
                int resRootX = find(parent, restriction[0]);
                int resRootY = find(parent, restriction[1]);

                if ((rootU == resRootX && rootV == resRootY) || (rootU == resRootY && rootV == resRootX)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                result[i] = true;
                parent[rootU] = rootV;
            } else {
                result[i] = false;
            }
        }

        return result;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}
