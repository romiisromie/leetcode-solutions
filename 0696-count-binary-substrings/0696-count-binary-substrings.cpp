#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    int countBinarySubstrings(string s) {
        int count = 0;
        int prev_length = 0;
        int curr_length = 1;
        int n = s.length();

        for (int i = 1; i < n; ++i) {
            if (s[i] == s[i - 1]) {
                curr_length++;
            } else {
                count += min(prev_length, curr_length);
                prev_length = curr_length;
                curr_length = 1;
            }
        }
        
        count += min(prev_length, curr_length);
        return count;
    }
};