#include <vector>
#include <algorithm>
#include <numeric>

class Solution {
private:
    std::vector<int> parent;

    int find(int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent[i]);
    }

    void unite(int i, int j) {
        int root_i = find(i);
        int root_j = find(j);
        if (root_i != root_j) {
            parent[root_i] = root_j;
        }
    }

public:
    bool gcdSort(std::vector<int>& nums) {
        int max_val = *std::max_element(nums.begin(), nums.end());
        parent.resize(max_val + 1);
        std::iota(parent.begin(), parent.end(), 0);

        std::vector<bool> visited(max_val + 1, false);
        for (int num : nums) {
            visited[num] = true;
        }

        std::vector<int> sieve(max_val + 1, 0);
        for (int i = 2; i <= max_val; ++i) {
            if (sieve[i] == 0) {
                for (int j = i; j <= max_val; j += i) {
                    sieve[j] = i;
                    if (visited[j]) {
                        unite(j, i);
                    }
                }
            }
        }

        std::vector<int> sorted_nums = nums;
        std::sort(sorted_nums.begin(), sorted_nums.end());

        for (size_t i = 0; i < nums.size(); ++i) {
            if (find(nums[i]) != find(sorted_nums[i])) {
                return false;
            }
        }

        return true;
    }
};