#include <vector>

class Solution {
public:
    bool isTrionic(std::vector<int>& nums) {
        int n = nums.size();
        if (n < 4) {
            return false;
        }

        int p = 0;
        while (p < n - 1 && nums[p] < nums[p + 1]) {
            p++;
        }
        if (p == 0 || p == n - 1) {
            return false;
        }

        int q = p;
        while (q < n - 1 && nums[q] > nums[q + 1]) {
            q++;
        }
        if (q == p || q == n - 1) {
            return false;
        }

        while (q < n - 1 && nums[q] < nums[q + 1]) {
            q++;
        }

        return q == n - 1;
    }
};