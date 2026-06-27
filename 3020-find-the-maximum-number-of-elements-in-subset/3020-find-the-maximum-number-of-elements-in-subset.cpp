#include <vector>
#include <unordered_map>
#include <algorithm>
#include <cmath>

class Solution {
public:
    int maximumLength(std::vector<int>& nums) {
        std::unordered_map<long long, int> counts;
        for (int num : nums) {
            counts[num]++;
        }

        int max_len = 1;

        if (counts.find(1) != counts.end()) {
            int ones = counts[1];
            if (ones % 2 == 0) {
                max_len = std::max(max_len, ones - 1);
            } else {
                max_len = std::max(max_len, ones);
            }
        }

        for (auto& [val, count] : counts) {
            if (val == 1) continue;

            long long current = val;
            int current_len = 0;

            while (counts.find(current) != counts.end() && counts[current] >= 2) {
                current_len += 2;
                current = current * current;
            }

            if (counts.find(current) != counts.end() && counts[current] >= 1) {
                current_len += 1;
            } else {
                current_len -= 1;
            }

            max_len = std::max(max_len, current_len);
        }

        return max_len;
    }
};