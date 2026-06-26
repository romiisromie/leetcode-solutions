#include <vector>
#include <algorithm>

class Solution {
public:
    int maxProduct(std::vector<int>& nums) {
        int n = nums.size();
        int max_prod = nums[0];
        int min_prod = nums[0];
        int ans = nums[0];

        for (int i = 1; i < n; ++i) {
            if (nums[i] < 0) {
                std::swap(max_prod, min_prod);
            }

            max_prod = std::max(nums[i], max_prod * nums[i]);
            min_prod = std::min(nums[i], min_prod * nums[i]);

            ans = std::max(ans, max_prod);
        }

        return ans;
    }
};