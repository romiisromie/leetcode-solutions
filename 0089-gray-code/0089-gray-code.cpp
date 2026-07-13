#include <vector>

using namespace std;

class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> result;
        int total_numbers = 1 << n;
        result.reserve(total_numbers);
        
        for (int i = 0; i < total_numbers; ++i) {
            result.push_back(i ^ (i >> 1));
        }
        
        return result;
    }
};