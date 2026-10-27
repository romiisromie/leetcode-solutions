import java.util.*;

public class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<Integer>> redAdj = new ArrayList<>(n);
        List<List<Integer>> blueAdj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            redAdj.add(new ArrayList<>());
            blueAdj.add(new ArrayList<>());
        }

        for (int[] edge : redEdges) {
            redAdj.get(edge[0]).add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            blueAdj.get(edge[0]).add(edge[1]);
        }

        int[][] distances = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        distances[0][0] = 0;
        distances[0][1] = 0;
        
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int color = curr[1];
            int nextDist = distances[node][color] + 1;

            if (color == 0) {
                for (int neighbor : blueAdj.get(node)) {
                    if (distances[neighbor][1] == -1) {
                        distances[neighbor][1] = nextDist;
                        queue.offer(new int[]{neighbor, 1});
                    }
                }
            } else {
                for (int neighbor : redAdj.get(node)) {
                    if (distances[neighbor][0] == -1) {
                        distances[neighbor][0] = nextDist;
                        queue.offer(new int[]{neighbor, 0});
                    }
                }
            }
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int rDist = distances[i][0];
            int bDist = distances[i][1];
            
            if (rDist == -1 && bDist == -1) {
                answer[i] = -1;
            } else if (rDist == -1) {
                answer[i] = bDist;
            } else if (bDist == -1) {
                answer[i] = rDist;
            } else {
                answer[i] = Math.min(rDist, bDist);
            }
        }

        return answer;
    }
}