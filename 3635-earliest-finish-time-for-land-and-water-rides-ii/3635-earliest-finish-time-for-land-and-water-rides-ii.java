import java.util.Arrays;

public class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(
            solve(landStartTime, landDuration, waterStartTime, waterDuration),
            solve(waterStartTime, waterDuration, landStartTime, landDuration)
        );
    }

    private int solve(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int m = start2.length;
        int[][] rides2 = new int[m][3];
        for (int i = 0; i < m; i++) {
            rides2[i][0] = start2[i];
            rides2[i][1] = dur2[i];
            rides2[i][2] = start2[i] + dur2[i];
        }

        Arrays.sort(rides2, (a, b) -> Integer.compare(a[0], b[0]));

        int[] prefMinDur = new int[m];
        prefMinDur[0] = rides2[0][1];
        for (int i = 1; i < m; i++) {
            prefMinDur[i] = Math.min(prefMinDur[i - 1], rides2[i][1]);
        }

        int[] suffMinEnd = new int[m];
        suffMinEnd[m - 1] = rides2[m - 1][2];
        for (int i = m - 2; i >= 0; i--) {
            suffMinEnd[i] = Math.min(suffMinEnd[i + 1], rides2[i][2]);
        }

        int minOverallFinish = Integer.MAX_VALUE;

        for (int i = 0; i < start1.length; i++) {
            int f1 = start1[i] + dur1[i];

            int low = 0, high = m - 1, splitIdx = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (rides2[mid][0] <= f1) {
                    splitIdx = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            int currentBest = Integer.MAX_VALUE;
            if (splitIdx != -1) {
                currentBest = Math.min(currentBest, f1 + prefMinDur[splitIdx]);
            }
            if (splitIdx + 1 < m) {
                currentBest = Math.min(currentBest, suffMinEnd[splitIdx + 1]);
            }

            minOverallFinish = Math.min(minOverallFinish, currentBest);
        }

        return minOverallFinish;
    }
}