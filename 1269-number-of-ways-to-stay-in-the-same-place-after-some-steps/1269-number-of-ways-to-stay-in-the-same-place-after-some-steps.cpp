#include <vector>
#include <algorithm>

class Solution {
public:
    int numWays(int steps, int arrLen) {
        int maxPos = std::min(steps, arrLen - 1);
        std::vector<long long> dp(maxPos + 1, 0);
        dp[0] = 1;
        long long MOD = 1000000007;

        for (int i = 1; i <= steps; ++i) {
            std::vector<long long> next_dp(maxPos + 1, 0);
            for (int j = 0; j <= maxPos; ++j) {
                next_dp[j] = dp[j];
                if (j > 0) {
                    next_dp[j] = (next_dp[j] + dp[j - 1]) % MOD;
                }
                if (j < maxPos) {
                    next_dp[j] = (next_dp[j] + dp[j + 1]) % MOD;
                }
            }
            dp = std::move(next_dp);
        }

        return dp[0];
    }
};