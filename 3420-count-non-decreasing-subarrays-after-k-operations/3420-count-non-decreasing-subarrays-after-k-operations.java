import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        Deque<Pair> dq = new ArrayDeque<>();
        
        int j = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            int num = nums[i];
            int count = 1;
            
            while (!dq.isEmpty() && dq.peekLast().num < num) {
                Pair top = dq.pollLast();
                count += top.count;
                cost += (long) (num - top.num) * top.count;
            }
            dq.offerLast(new Pair(num, count));
            
            while (cost > k) {
                Pair first = dq.peekFirst();
                cost -= (long) (first.num - nums[j]);
                j--;
                if (first.count > 1) {
                    first.count--;
                } else {
                    dq.pollFirst();
                }
            }
            
            ans += (j - i + 1);
        }
        
        return ans;
    }

    private static class Pair {
        int num;
        int count;

        Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}