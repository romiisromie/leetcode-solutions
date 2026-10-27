import java.util.*;

public class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(id);
        visited[id] = true;

        int currentLevel = 0;
        while (!queue.isEmpty() && currentLevel < level) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int friend : friends[curr]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.offer(friend);
                    }
                }
            }
            currentLevel++;
        }

        Map<String, Integer> frequencyMap = new HashMap<>();
        while (!queue.isEmpty()) {
            int friendId = queue.poll();
            for (String video : watchedVideos.get(friendId)) {
                frequencyMap.put(video, frequencyMap.getOrDefault(video, 0) + 1);
            }
        }

        List<String> result = new ArrayList<>(frequencyMap.keySet());
        result.sort((a, b) -> {
            int freqA = frequencyMap.get(a);
            int freqB = frequencyMap.get(b);
            if (freqA != freqB) {
                return Integer.compare(freqA, freqB);
            }
            return a.compareTo(b);
        });

        return result;
    }
}