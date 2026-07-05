#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<int> pathsWithMaxScore(vector<string>& board) {
        int n = board.size();
        int MOD = 1e9 + 7;

        vector<vector<int>> max_score(n, vector<int>(n, -1));
        vector<vector<int>> path_count(n, vector<int>(n, 0));

        max_score[n - 1][n - 1] = 0;
        path_count[n - 1][n - 1] = 1;

        for (int r = n - 1; r >= 0; --r) {
            for (int c = n - 1; c >= 0; --c) {
                if (board[r][c] == 'X' || max_score[r][c] == -1) {
                    continue;
                }

                int current_score = max_score[r][c];
                int current_paths = path_count[r][c];

                int dr[] = {-1, 0, -1};
                int dc[] = {0, -1, -1};

                for (int i = 0; i < 3; ++i) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr >= 0 && nc >= 0 && board[nr][nc] != 'X') {
                        int cell_value = (board[nr][nc] == 'E' || board[nr][nc] == 'S') ? 0 : (board[nr][nc] - '0');
                        int next_score = current_score + cell_value;

                        if (next_score > max_score[nr][nc]) {
                            max_score[nr][nc] = next_score;
                            path_count[nr][nc] = current_paths;
                        } else if (next_score == max_score[nr][nc]) {
                            path_count[nr][nc] = (path_count[nr][nc] + current_paths) % MOD;
                        }
                    }
                }
            }
        }

        if (max_score[0][0] == -1) {
            return {0, 0};
        }

        return {max_score[0][0], path_count[0][0]};
    }
};