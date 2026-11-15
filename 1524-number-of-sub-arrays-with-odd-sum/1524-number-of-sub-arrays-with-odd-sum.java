class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        long result = 0;
        int prefixSum = 0;
        int evenCount = 1;
        int oddCount = 0;

        for (int num : arr) {
            prefixSum += num;
            if (prefixSum % 2 == 0) {
                result += oddCount;
                evenCount++;
            } else {
                result += evenCount;
                oddCount++;
            }
        }

        return (int) (result % mod);
    }
}