#include <vector>
#include <string>

using namespace std;

class Solution {
private:
    int countBits(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);
            n >>= 1;
        }
        return count;
    }

public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<string> result;
        
        for (int h = 0; h < 12; ++h) {
            for (int m = 0; m < 60; ++m) {
                if (countBits(h) + countBits(m) == turnedOn) {
                    string minutes = (m < 10) ? "0" + to_string(m) : to_string(m);
                    result.push_back(to_string(h) + ":" + minutes);
                }
            }
        }
        
        return result;
    }
};