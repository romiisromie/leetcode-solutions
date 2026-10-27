class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minLandEnd = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minLandEnd = Math.min(minLandEnd, landStartTime[i] + landDuration[i]);
        }
        
        int landThenWater = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; j++) {
            int currentFinish = Math.max(minLandEnd, waterStartTime[j]) + waterDuration[j];
            landThenWater = Math.min(landThenWater, currentFinish);
        }

        int minWaterEnd = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; j++) {
            minWaterEnd = Math.min(minWaterEnd, waterStartTime[j] + waterDuration[j]);
        }

        int waterThenLand = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int currentFinish = Math.max(minWaterEnd, landStartTime[i]) + landDuration[i];
            waterThenLand = Math.min(waterThenLand, currentFinish);
        }

        return Math.min(landThenWater, waterThenLand);
    }
}