#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    vector<int> sumAndMultiply(string s, vector<vector<int>>& queries) {
        int m = s.length();
        int MOD = 1e9 + 7;

        vector<long long> p10(m + 1, 1);
        for (int i = 1; i <= m; ++i) {
            p10[i] = (p10[i - 1] * 10) % MOD;
        }

        vector<int> count_nonzero(m + 1, 0);
        vector<long long> prefix_sum(m + 1, 0);
        vector<long long> prefix_val(m + 1, 0);

        for (int i = 0; i < m; ++i) {
            count_nonzero[i + 1] = count_nonzero[i];
            prefix_sum[i + 1] = prefix_sum[i];
            prefix_val[i + 1] = prefix_val[i];

            if (s[i] != '0') {
                int digit = s[i] - '0';
                count_nonzero[i + 1]++;
                prefix_sum[i + 1] += digit;
                prefix_val[i + 1] = (prefix_val[i] * 10 + digit) % MOD;
            }
        }

        vector<int> answer;
        answer.reserve(queries.size());

        for (const auto& q : queries) {
            int l = q[0];
            int r = q[1];

            int total_nonzero_r = count_nonzero[r + 1];
            int total_nonzero_l = count_nonzero[l];
            int num_digits = total_nonzero_r - total_nonzero_l;

            if (num_digits == 0) {
                answer.push_back(0);
                continue;
            }

            long long sum = prefix_sum[r + 1] - prefix_sum[l];

            long long val_r = prefix_val[r + 1];
            long long val_l = prefix_val[l];
            long long x = (val_r - (val_l * p10[num_digits]) % MOD + MOD) % MOD;

            long long res = (x * (sum % MOD)) % MOD;
            answer.push_back(res);
        }

        return answer;
    }
};