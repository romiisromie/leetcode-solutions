public class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long mod = 1000000007;
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        long maxKadane = kadane(arr, Math.min(k, 2));

        if (k <= 2) {
            return (int) (maxKadane % mod);
        }

        if (totalSum > 0) {
            return (int) ((maxKadane + (k - 2) * totalSum) % mod);
        } else {
            return (int) (maxKadane % mod);
        }
    }

    private long kadane(int[] arr, int repetitions) {
        long maxSoFar = 0;
        long maxEndingHere = 0;
        int n = arr.length;

        for (int i = 0; i < n * repetitions; i++) {
            maxEndingHere += arr[i % n];
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
            }
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
            }
        }
        return maxSoFar;
    }
}