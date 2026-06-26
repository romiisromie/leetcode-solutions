#include <vector>

class Solution {
private:
    std::vector<int> tree;
    int offset;

    void update(int idx, int val) {
        idx += offset;
        while (idx < tree.size()) {
            tree[idx] += val;
            idx += idx & (-idx);
        }
    }

    int query(int idx) {
        idx += offset;
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

public:
    long long countMajoritySubarrays(std::vector<int>& nums, int target) {
        int n = nums.size();
        tree.assign(2 * n + 2, 0);
        offset = n + 1;

        int current_sum = 0;
        long long total_subarrays = 0;

        update(0, 1);

        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                current_sum += 1;
            } else {
                current_sum -= 1;
            }

            total_subarrays += query(current_sum - 1);
            update(current_sum, 1);
        }

        return total_subarrays;
    }
};