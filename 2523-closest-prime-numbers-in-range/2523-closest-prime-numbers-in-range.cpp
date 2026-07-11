#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

class Solution {
public:
    vector<int> closestPrimes(int left, int right) {
        vector<bool> isPrime(right + 1, true);
        if (right >= 0) isPrime[0] = false;
        if (right >= 1) isPrime[1] = false;

        for (int i = 2; i * i <= right; ++i) {
            if (isPrime[i]) {
                for (int j = i * i; j <= right; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        vector<int> primes;
        for (int i = max(2, left); i <= right; ++i) {
            if (isPrime[i]) {
                primes.push_back(i);
            }
        }

        if (primes.size() < 2) {
            return {-1, -1};
        }

        int min_diff = INT_MAX;
        vector<int> ans = {-1, -1};

        for (size_t i = 0; i < primes.size() - 1; ++i) {
            int diff = primes[i + 1] - primes[i];
            if (diff < min_diff) {
                min_diff = diff;
                ans = {primes[i], primes[i + 1]};
                if (min_diff <= 2) {
                    break;
                }
            }
        }

        return ans;
    }
};