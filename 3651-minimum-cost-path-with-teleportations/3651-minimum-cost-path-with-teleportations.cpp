#include <vector>
#include <algorithm>
#include <map>

class Solution {
public:
    int minCost(std::vector<std::vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        const int INF = 1e9;
        
        std::vector<std::vector<std::vector<int>>> dp(k + 1, std::vector<std::vector<int>>(m, std::vector<int>(n, INF)));
        dp[0][0][0] = 0;
        
        std::map<int, std::vector<std::pair<int, int>>, std::greater<int>> valueToCells;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                valueToCells[grid[i][j]].push_back({i, j});
            }
        }
        
        for (int t = 0; t <= k; ++t) {
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (dp[t][i][j] == INF) continue;
                    
                    if (j + 1 < n) {
                        dp[t][i][j + 1] = std::min(dp[t][i][j + 1], dp[t][i][j] + grid[i][j + 1]);
                    }
                    if (i + 1 < m) {
                        dp[t][i + 1][j] = std::min(dp[t][i + 1][j], dp[t][i][j] + grid[i + 1][j]);
                    }
                }
            }
            
            if (t < k) {
                int min_cost_from_higher_src = INF;
                
                for (const auto& group : valueToCells) {
                    int current_group_min = INF;
                    for (const auto& cell : group.second) {
                        current_group_min = std::min(current_group_min, dp[t][cell.first][cell.second]);
                    }
                    
                    min_cost_from_higher_src = std::min(min_cost_from_higher_src, current_group_min);
                    
                    if (min_cost_from_higher_src != INF) {
                        for (const auto& cell : group.second) {
                            dp[t + 1][cell.first][cell.second] = std::min(dp[t + 1][cell.first][cell.second], min_cost_from_higher_src);
                        }
                    }
                }
            }
        }
        
        int ans = INF;
        for (int t = 0; t <= k; ++t) {
            ans = std::min(ans, dp[t][m - 1][n - 1]);
        }
        
        return ans == INF ? -1 : ans;
    }
};