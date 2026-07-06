class Solution {
public:
    int countPrimeSetBits(int left, int right) {
        int prime_mask = (1 << 2) | (1 << 3) | (1 << 5) | (1 << 7) | 
                         (1 << 11) | (1 << 13) | (1 << 17) | (1 << 19) | 
                         (1 << 23) | (1 << 29) | (1 << 31);
        int count = 0;
        for (int i = left; i <= right; ++i) {
            int bits = __builtin_popcount(i);
            if ((prime_mask >> bits) & 1) {
                count++;
            }
        }
        return count;
    }
};