#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int projectionArea(vector<vector<int>>& grid) {
        int n = grid.size();
        int area = 0;

        for (int i = 0; i < n; ++i) {
            int max_row = 0;
            int max_col = 0;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    area++; 
                }
                max_row = max(max_row, grid[i][j]);
                max_col = max(max_col, grid[j][i]);
            }
            area += max_row + max_col;
        }

        return area;
    }
};