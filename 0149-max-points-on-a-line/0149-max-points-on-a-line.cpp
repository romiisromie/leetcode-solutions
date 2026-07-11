#include <vector>
#include <algorithm>
#include <map>
#include <numeric>

using namespace std;

class Solution {
public:
    int maxPoints(vector<vector<int>>& points) {
        int n = points.size();
        if (n <= 2) return n;

        int max_points = 0;

        for (int i = 0; i < n; ++i) {
            map<pair<int, int>, int> slopes;
            int duplicate = 1;
            int current_max = 0;

            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }

                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                slopes[{dx, dy}]++;
                current_max = max(current_max, slopes[{dx, dy}]);
            }
            max_points = max(max_points, current_max + duplicate);
        }

        return max_points;
    }
};