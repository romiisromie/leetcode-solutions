#include <vector>
#include <algorithm>

class Solution {
public:
    int minRemoval(std::vector<int>& nums, int k) {
        int n = nums.size();
        std::sort(nums.begin(), nums.end());
        
        int max_kept = 0;
        int left = 0;
        
        for (int right = 0; right < n; ++right) {
            while ((long long)nums[right] > (long long)nums[left] * k) {
                left++;
            }
            max_kept = std::max(max_kept, right - left + 1);
        }
        
        return n - max_kept;
    }
};
