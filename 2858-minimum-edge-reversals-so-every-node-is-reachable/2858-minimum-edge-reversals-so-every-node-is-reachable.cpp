#include <vector>
#include <algorithm>

class Solution {
private:
    struct Edge {
        int to;
        int weight;
    };

    std::vector<std::vector<Edge>> adj;
    std::vector<int> ans;

    int dfs1(int u, int p) {
        int total_reversals = 0;
        for (const auto& edge : adj[u]) {
            if (edge.to != p) {
                total_reversals += edge.weight + dfs1(edge.to, u);
            }
        }
        return total_reversals;
    }

    void dfs2(int u, int p, int current_reversals) {
        ans[u] = current_reversals;
        for (const auto& edge : adj[u]) {
            if (edge.to != p) {
                int next_reversals = current_reversals + (edge.weight == 0 ? 1 : -1);
                dfs2(edge.to, u, next_reversals);
            }
        }
    }

public:
    std::vector<int> minEdgeReversals(int n, std::vector<std::vector<int>>& edges) {
        adj.assign(n, std::vector<Edge>());
        ans.assign(n, 0);

        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u].push_back({v, 0});
            adj[v].push_back({u, 1});
        }

        int root_reversals = dfs1(0, -1);
        dfs2(0, -1, root_reversals);

        return ans;
    }
};