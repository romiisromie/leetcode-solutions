import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] result = new int[n][n][3];
        int[][][] outdegree = new int[n][n][3];

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                outdegree[m][c][1] = graph[m].length;
                int catMoves = 0;
                for (int neighbor : graph[c]) {
                    if (neighbor != 0) {
                        catMoves++;
                    }
                }
                outdegree[m][c][2] = catMoves;
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int c = 1; c < n; c++) {
            result[0][c][1] = 1;
            result[0][c][2] = 1;
            queue.offer(new int[]{0, c, 1, 1});
            queue.offer(new int[]{0, c, 2, 1});
        }

        for (int m = 1; m < n; m++) {
            result[m][m][1] = 2;
            result[m][m][2] = 2;
            queue.offer(new int[]{m, m, 1, 2});
            queue.offer(new int[]{m, m, 2, 2});
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int m = state[0];
            int c = state[1];
            int turn = state[2];
            int res = state[3];

            if (m == 1 && c == 2 && turn == 1) {
                return res;
            }

            int prevTurn = (turn == 1) ? 2 : 1;

            if (prevTurn == 1) {
                for (int prevM : graph[m]) {
                    if (result[prevM][c][prevTurn] == 0) {
                        if (res == 1) {
                            result[prevM][c][prevTurn] = 1;
                            queue.offer(new int[]{prevM, c, prevTurn, 1});
                        } else {
                            outdegree[prevM][c][prevTurn]--;
                            if (outdegree[prevM][c][prevTurn] == 0) {
                                result[prevM][c][prevTurn] = 2;
                                queue.offer(new int[]{prevM, c, prevTurn, 2});
                            }
                        }
                    }
                }
            } else {
                for (int prevC : graph[c]) {
                    if (prevC == 0) {
                        continue;
                    }
                    if (result[m][prevC][prevTurn] == 0) {
                        if (res == 2) {
                            result[m][prevC][prevTurn] = 2;
                            queue.offer(new int[]{m, prevC, prevTurn, 2});
                        } else {
                            outdegree[m][prevC][prevTurn]--;
                            if (outdegree[m][prevC][prevTurn] == 0) {
                                result[m][prevC][prevTurn] = 1;
                                queue.offer(new int[]{m, prevC, prevTurn, 1});
                            }
                        }
                    }
                }
            }
        }

        return result[1][2][1];
    }
}