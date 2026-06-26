#include <vector>
#include <cmath>

class Solution {
public:
    std::vector<int> constructTransformedArray(std::vector<int>& nums) {
        int n = nums.size();
        std::vector<int> result(n);

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                result[i] = nums[i];
            } else {
                int target_idx = (i + nums[i]) % n;
                if (target_idx < 0) {
                    target_idx += n;
                }
                result[i] = nums[target_idx];
            }
        }

        return result;
    }
};