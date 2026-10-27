import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        List<Set<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new HashSet<>());
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        List<Integer> oddNodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (adj.get(i).size() % 2 != 0) {
                oddNodes.add(i);
            }
        }

        if (oddNodes.isEmpty()) {
            return true;
        }

        if (oddNodes.size() == 2) {
            int u = oddNodes.get(0);
            int v = oddNodes.get(1);

            if (!adj.get(u).contains(v)) {
                return true;
            }

            for (int i = 1; i <= n; i++) {
                if (i != u && i != v && !adj.get(u).contains(i) && !adj.get(v).contains(i)) {
                    return true;
                }
            }
            return false;
        }

        if (oddNodes.size() == 4) {
            int a = oddNodes.get(0);
            int b = oddNodes.get(1);
            int c = oddNodes.get(2);
            int d = oddNodes.get(3);

            if (!adj.get(a).contains(b) && !adj.get(c).contains(d)) return true;
            if (!adj.get(a).contains(c) && !adj.get(b).contains(d)) return true;
            if (!adj.get(a).contains(d) && !adj.get(b).contains(c)) return true;
            
            return false;
        }

        return false;
    }
}