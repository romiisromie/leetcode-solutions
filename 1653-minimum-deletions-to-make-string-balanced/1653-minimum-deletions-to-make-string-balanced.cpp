#include <string>
#include <algorithm>

class Solution {
public:
    int minimumDeletions(std::string s) {
        int b_count = 0;
        int deletions = 0;

        for (char c : s) {
            if (c == 'b') {
                b_count++;
            } else {
                deletions = std::min(deletions + 1, b_count);
            }
        }

        return deletions;
    }
};