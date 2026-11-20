import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int inc = dp.getOrDefault(num, 0) + 1;
            int keep = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num + 1, inc);
            dp.put(num, keep);
            ans = Math.max(ans, Math.max(inc, keep));
        }

        return ans;
    }
}