#include <algorithm>

using namespace std;

class Solution {
public:
    int binaryGap(int n) {
        int last_pos = -1;
        int max_gap = 0;
        int pos = 0;

        while (n > 0) {
            if (n & 1) {
                if (last_pos != -1) {
                    max_gap = max(max_gap, pos - last_pos);
                }
                last_pos = pos;
            }
            n >>= 1;
            pos++;
        }

        return max_gap;
    }
};