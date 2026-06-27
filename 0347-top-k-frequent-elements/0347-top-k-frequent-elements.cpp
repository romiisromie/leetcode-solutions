#include <vector>
#include <unordered_map>

class Solution {
public:
    std::vector<int> topKFrequent(std::vector<int>& nums, int k) {
        std::unordered_map<int, int> counts;
        for (int num : nums) {
            counts[num]++;
        }

        int n = nums.size();
        std::vector<std::vector<int>> buckets(n + 1);
        for (const auto& [num, freq] : counts) {
            buckets[freq].push_back(num);
        }

        std::vector<int> result;
        for (int i = n; i >= 0 && result.size() < k; --i) {
            for (int num : buckets[i]) {
                result.push_back(num);
                if (result.size() == k) {
                    break;
                }
            }
        }

        return result;
    }
};