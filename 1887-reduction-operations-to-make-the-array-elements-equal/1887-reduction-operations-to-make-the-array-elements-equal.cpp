#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        
        int operations = 0;
        int current_weight = 0;
        int n = nums.size();
        
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i - 1]) {
                current_weight++;
            }
            operations += current_weight;
        }
        
        return operations;
    }
};