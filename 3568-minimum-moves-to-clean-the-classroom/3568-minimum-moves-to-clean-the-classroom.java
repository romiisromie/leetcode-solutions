import java.util.*;

public class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startRow = -1, startCol = -1;
        List<int[]> litterList = new ArrayList<>();
        List<int[]> resetList = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (ch == 'L') {
                    litterList.add(new int[]{i, j});
                } else if (ch == 'R') {
                    resetList.add(new int[]{i, j});
                }
            }
        }
        
        int numLitter = litterList.size();
        if (numLitter == 0) {
            return 0;
        }
        
        List<int[]> allPoints = new ArrayList<>();
        allPoints.add(new int[]{startRow, startCol});
        for (int[] p : litterList) {
            allPoints.add(p);
        }
        for (int[] p : resetList) {
            allPoints.add(p);
        }
        
        int totalPoints = allPoints.size();
        int[][][] dist = new int[totalPoints][m][n];
        for (int i = 0; i < totalPoints; i++) {
            for (int r = 0; r < m; r++) {
                Arrays.fill(dist[i][r], -1);
            }
            int[] pt = allPoints.get(i);
            bfs(pt[0], pt[1], dist[i], classroom, m, n);
        }
        
        int numReset = resetList.size();
        int totalGraphNodes = 1 + numLitter + numReset;
        
        int[][][] dp = new int[1 << numLitter][totalGraphNodes][energy + 1];
        for (int i = 0; i < (1 << numLitter); i++) {
            for (int j = 0; j < totalGraphNodes; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dp[0][0][energy] = 0;
        pq.offer(new int[]{0, 0, 0, energy});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int mask = curr[1];
            int u = curr[2];
            int e = curr[3];
            
            if (cost > dp[mask][u][e]) {
                continue;
            }
            if (mask == (1 << numLitter) - 1) {
                return cost;
            }
            
            for (int v = 1; v <= numLitter; v++) {
                if ((mask & (1 << (v - 1))) == 0) {
                    int[] vPt = allPoints.get(v);
                    int d = dist[u][vPt[0]][vPt[1]];
                    if (d != -1 && e >= d) {
                        int nextMask = mask | (1 << (v - 1));
                        int nextE = e - d;
                        if (classroom[vPt[0]].charAt(vPt[1]) == 'R') {
                            nextE = energy;
                        }
                        if (cost + d < dp[nextMask][v][nextE]) {
                            dp[nextMask][v][nextE] = cost + d;
                            pq.offer(new int[]{cost + d, nextMask, v, nextE});
                        }
                    }
                }
            }
            
            for (int r = 0; r < numReset; r++) {
                int v = 1 + numLitter + r;
                int[] rPt = allPoints.get(v);
                int d = dist[u][rPt[0]][rPt[1]];
                if (d != -1 && e >= d) {
                    int nextE = energy;
                    if (cost + d < dp[mask][v][nextE]) {
                        dp[mask][v][nextE] = cost + d;
                        pq.offer(new int[]{cost + d, mask, v, nextE});
                    }
                }
            }
        }
        
        return -1;
    }

    private void bfs(int startR, int startC, int[][] dMap, String[] classroom, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        dMap[startR][startC] = 0;
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            int curD = dMap[r][c];
            
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X' && dMap[nr][nc] == -1) {
                    dMap[nr][nc] = curD + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}