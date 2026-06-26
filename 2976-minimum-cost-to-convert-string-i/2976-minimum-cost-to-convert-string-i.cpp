#include <vector>
#include <string>
#include <algorithm>

class Solution {
public:
    long long minimumCost(std::string source, std::string target, std::vector<char>& original, std::vector<char>& changed, std::vector<int>& cost) {
        const long long INF = 1e18;
        std::vector<std::vector<long long>> dist(26, std::vector<long long>(26, INF));

        for (int i = 0; i < 26; ++i) {
            dist[i][i] = 0;
        }

        for (size_t i = 0; i < original.size(); ++i) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = std::min(dist[u][v], (long long)cost[i]);
        }

        for (int k = 0; k < 26; ++k) {
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < 26; ++j) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = std::min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        long long totalCost = 0;
        for (size_t i = 0; i < source.length(); ++i) {
            int u = source[i] - 'a';
            int v = target[i] - 'a';
            if (dist[u][v] == INF) {
                return -1;
            }
            totalCost += dist[u][v];
        }

        return totalCost;
    }
};