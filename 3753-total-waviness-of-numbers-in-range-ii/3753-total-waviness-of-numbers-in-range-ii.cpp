#include <string>
#include <vector>
#include <cstring>

class Solution {
private:
    long long memo[16][11][11][2][2][16];

    long long slove(const std::string& S, int idx, int pprev, int prev, bool is_less, bool is_started, int waviness) {
        if (idx == S.length()) {
            return is_started ? waviness : 0;
        }

        if (memo[idx][pprev + 1][prev + 1][is_less][is_started][waviness] != -1) {
            return memo[idx][pprev + 1][prev + 1][is_less][is_started][waviness];
        }

        long long total = 0;
        int limit = is_less ? 9 : (S[idx] - '0');

        for (int digit = 0; digit <= limit; ++digit) {
            bool next_less = is_less || (digit < (S[idx] - '0'));
            
            if (!is_started) {
                if (digit == 0) {
                    total += slove(S, idx + 1, -1, -1, next_less, false, 0);
                } else {
                    total += slove(S, idx + 1, -1, digit, next_less, true, 0);
                }
            } else {
                int next_waviness = waviness;
                if (pprev != -1) {
                    if (prev > pprev && prev > digit) {
                        next_waviness++;
                    } else if (prev < pprev && prev < digit) {
                        next_waviness++;
                    }
                }
                total += slove(S, idx + 1, prev, digit, next_less, true, next_waviness);
            }
        }

        return memo[idx][pprev + 1][prev + 1][is_less][is_started][waviness] = total;
    }

    long long getTotalWaviness(long long num) {
        if (num < 100) return 0;
        std::string S = std::to_string(num);
        std::memset(memo, -1, sizeof(memo));
        return slove(S, 0, -1, -1, false, false, 0);
    }

public:
    long long totalWaviness(long long num1, long long num2) {
        return getTotalWaviness(num2) - getTotalWaviness(num1 - 1);
    }
};