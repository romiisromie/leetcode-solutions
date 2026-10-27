import java.util.Arrays;

public class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] inMST = new boolean[n];
        
        minDist[0] = 0;
        int totalCost = 0;
        int edgesUsed = 0;

        while (edgesUsed < n) {
            int currNode = -1;
            int currMinDist = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!inMST[i] && minDist[i] < currMinDist) {
                    currMinDist = minDist[i];
                    currNode = i;
                }
            }

            inMST[currNode] = true;
            totalCost += currMinDist;
            edgesUsed++;

            for (int nextNode = 0; nextNode < n; nextNode++) {
                if (!inMST[nextNode]) {
                    int dist = Math.abs(points[currNode][0] - points[nextNode][0])
                             + Math.abs(points[currNode][1] - points[nextNode][1]);
                    
                    if (dist < minDist[nextNode]) {
                        minDist[nextNode] = dist;
                    }
                }
            }
        }

        return totalCost;
    }
}