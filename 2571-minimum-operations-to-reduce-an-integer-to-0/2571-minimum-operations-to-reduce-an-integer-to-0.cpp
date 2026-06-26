class Solution {
public:
    int minOperations(int n) {
        int ops = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if ((n & 2) == 2 && n > 2) {
                    n += 1;
                } else {
                    n -= 1;
                }
                ops++;
            }
            n >>= 1;
        }
        return ops;
    }
};