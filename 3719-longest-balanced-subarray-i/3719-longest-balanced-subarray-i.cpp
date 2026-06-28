#include <vector>
#include <unordered_set>
#include <algorithm>

class Solution {
public:
    int longestBalanced(std::vector<int>& nums) {
        int n = nums.size();
        int max_len = 0;

        for (int i = 0; i < n; ++i) {
            std::unordered_set<int> visited;
            int distinct_even = 0;
            int distinct_odd = 0;

            for (int j = i; j < n; ++j) {
                if (visited.find(nums[j]) == visited.end()) {
                    visited.insert(nums[j]);
                    if (nums[j] % 2 == 0) {
                        distinct_even++;
                    } else {
                        distinct_odd++;
                    }
                }

                if (distinct_even == distinct_odd) {
                    max_len = std::max(max_len, j - i + 1);
                }
            }
        }

        return max_len;
    }
};