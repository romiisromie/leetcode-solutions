import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] hasIncomingEdge = new boolean[n];
        
        for (List<Integer> edge : edges) {
            hasIncomingEdge[edge.get(1)] = true;
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasIncomingEdge[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
}