import java.util.*;

public class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int busId = 0; busId < routes.length; busId++) {
            for (int stop : routes[busId]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(busId);
            }
        }

        if (!stopToBuses.containsKey(source) || !stopToBuses.containsKey(target)) {
            return -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        boolean[] visitedBuses = new boolean[routes.length];

        queue.offer(source);
        visitedStops.add(source);

        int busCount = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            busCount++;

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                for (int busId : stopToBuses.get(currentStop)) {
                    if (visitedBuses[busId]) {
                        continue;
                    }
                    visitedBuses[busId] = true;

                    for (int nextStop : routes[busId]) {
                        if (nextStop == target) {
                            return busCount;
                        }
                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}