#include <vector>

using namespace std;

class Solution {
public:
    bool findRotation(vector<vector<int>>& mat, vector<vector<int>>& target) {
        int n = mat.size();
        bool rot0 = true, rot90 = true, rot180 = true, rot270 = true;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] != target[i][j]) rot0 = false;
                if (mat[i][j] != target[j][n - 1 - i]) rot90 = false;
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) rot180 = false;
                if (mat[i][j] != target[n - 1 - j][i]) rot270 = false;
            }
        }

        return rot0 || rot90 || rot180 || rot270;
    }
};