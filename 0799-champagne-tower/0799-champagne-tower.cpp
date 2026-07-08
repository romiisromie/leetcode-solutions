#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        vector<double> tower(101, 0.0);
        tower[0] = poured;

        for (int i = 0; i < query_row; ++i) {
            vector<double> next_row(101, 0.0);
            for (int j = 0; j <= i; ++j) {
                if (tower[j] > 1.0) {
                    double excess = (tower[j] - 1.0) / 2.0;
                    next_row[j] += excess;
                    next_row[j + 1] += excess;
                }
            }
            tower = next_row;
        }

        return min(1.0, tower[query_glass]);
    }
};