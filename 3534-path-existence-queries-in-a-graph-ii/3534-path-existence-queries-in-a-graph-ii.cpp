#include <vector>
#include <algorithm>
#include <numeric>
#include <cmath>

using namespace std;

class Solution {
public:
    vector<int> pathExistenceQueries(int n, vector<int>& nums, int maxDiff, vector<vector<int>>& queries) {
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return nums[i] < nums[j];
        });

        vector<int> pos(n);
        for (int i = 0; i < n; ++i) {
            pos[idx[i]] = i;
        }

        int m = 18;
        vector<vector<int>> f(n, vector<int>(m));
        
        int r = 0;
        for (int i = 0; i < n; ++i) {
            while (r + 1 < n && nums[idx[r + 1]] - nums[idx[i]] <= maxDiff) {
                r++;
            }
            f[i][0] = r;
        }

        for (int k = 1; k < m; ++k) {
            for (int i = 0; i < n; ++i) {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        vector<int> ans;
        ans.reserve(queries.size());

        for (const auto& q : queries) {
            int u = q[0], v = q[1];
            int i = pos[u], j = pos[v];
            
            if (i == j) {
                ans.push_back(0);
                continue;
            }
            if (i > j) {
                swap(i, j);
            }

            int d = 0;
            for (int k = m - 1; k >= 0; --k) {
                if (f[i][k] < j) {
                    d |= (1 << k);
                    i = f[i][k];
                }
            }

            if (f[i][0] < j) {
                ans.push_back(-1);
            } else {
                ans.push_back(d + 1);
            }
        }

        return ans;
    }
};