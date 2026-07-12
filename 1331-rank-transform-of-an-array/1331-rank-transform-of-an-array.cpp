#include <vector>
#include <algorithm>
#include <unordered_map>

class Solution {
public:
    std::vector<int> arrayRankTransform(std::vector<int>& arr) {
        std::vector<int> sorted_arr = arr;
        std::sort(sorted_arr.begin(), sorted_arr.end());
        
        std::unordered_map<int, int> ranks;
        int rank = 1;
        
        for (int num : sorted_arr) {
            if (ranks.find(num) == ranks.end()) {
                ranks[num] = rank++;
            }
        }
        
        std::vector<int> result;
        result.reserve(arr.size());
        for (int num : arr) {
            result.push_back(ranks[num]);
        }
        
        return result;
    }
};