#include <vector>

class Solution {
private:
    void dfs(std::vector<std::vector<char>>& grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') {
            return;
        }
        
        grid[r][c] = '0';
        
        dfs(grid, r + 1, c, m, n);
        dfs(grid, r - 1, c, m, n);
        dfs(grid, r, c + 1, m, n);
        dfs(grid, r, c - 1, m, n);
    }

public:
    int numIslands(std::vector<std::vector<char>>& grid) {
        if (grid.empty()) return 0;
        
        int m = grid.size();
        int n = grid[0].size();
        int count = 0;
        
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c, m, n);
                }
            }
        }
        
        return count;
    }
};