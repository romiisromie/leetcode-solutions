#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class Solution {
private:
    bool isValid(int n, const vector<vector<pair<int, int>>>& adj, int min_score, long long k) {
        vector<long long> dist(n, -1);
        priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;

        dist[0] = 0;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [curr_cost, u] = pq.top();
            pq.pop();

            if (u == n - 1) return true;
            if (curr_cost > dist[u]) continue;

            for (const auto& edge : adj[u]) {
                int v = edge.first;
                int cost = edge.second;

                if (cost < min_score) continue;

                long long next_cost = curr_cost + cost;
                if (next_cost <= k && (dist[v] == -1 || next_cost < dist[v])) {
                    dist[v] = next_cost;
                    pq.push({next_cost, v});
                }
            }
        }
        return false;
    }

public:
    int findMaxPathScore(vector<vector<int>>& edges, vector<bool>& online, long long k) {
        int n = online.size();
        vector<vector<pair<int, int>>> adj(n);
        int max_cost = 0;

        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            if (online[u] && online[v]) {
                adj[u].push_back({v, cost});
                max_cost = max(max_cost, cost);
            }
        }

        int left = 0, right = max_cost;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(n, adj, mid, k)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
};