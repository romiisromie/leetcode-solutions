import java.util.*;

public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] time : times) {
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }

        int[] signalTimes = new int[n + 1];
        Arrays.fill(signalTimes, Integer.MAX_VALUE);
        signalTimes[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currTime = curr[1];

            if (currTime > signalTimes[currNode]) {
                continue;
            }

            for (int[] edge : adj.get(currNode)) {
                int neighbor = edge[0];
                int time = edge[1];

                if (signalTimes[currNode] + time < signalTimes[neighbor]) {
                    signalTimes[neighbor] = signalTimes[currNode] + time;
                    pq.offer(new int[]{neighbor, signalTimes[neighbor]});
                }
            }
        }

        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (signalTimes[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxDelay = Math.max(maxDelay, signalTimes[i]);
        }

        return maxDelay;
    }
}