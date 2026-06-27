#include <vector>

class Solution {
private:
    bool isBalanced(int x) {
        std::vector<int> counts(10, 0);
        while (x > 0) {
            int digit = x % 10;
            if (digit == 0) return false;
            counts[digit]++;
            x /= 10;
        }
        for (int d = 1; d <= 9; ++d) {
            if (counts[d] > 0 && counts[d] != d) {
                return false;
            }
        }
        return true;
    }

public:
    int nextBeautifulNumber(int n) {
        int x = n + 1;
        while (true) {
            if (isBalanced(x)) {
                return x;
            }
            x++;
        }
    }
};