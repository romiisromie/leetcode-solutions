import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] c;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        c = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) {
            return 1;
        }
        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        long ways = (c[left.size() + right.size()][left.size()] * leftWays) % MOD;
        return (ways * rightWays) % MOD;
    }
}
