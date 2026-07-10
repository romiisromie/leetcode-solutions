#include <vector>

using namespace std;

class Solution {
public:
    int rearrangeSticks(int n, int k) {
        int MOD = 1e9 + 7;
        vector<long long> dp(k + 1, 0);
        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int j = min(i, k); j >= 1; --j) {
                dp[j] = (dp[j - 1] + (i - 1) * dp[j]) % MOD;
            }
            dp[0] = 0;
        }

        return dp[k];
    }
};