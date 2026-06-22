class Solution(object):
    def minimumCost(self, source, target, original, changed, cost):
        dist = [[float('inf')] * 26 for _ in range(26)]
        
        for i in range(26):
            dist[i][i] = 0
            
        for u, v, w in zip(original, changed, cost):
            i = ord(u) - ord('a')
            j = ord(v) - ord('a')
            if w < dist[i][j]:
                dist[i][j] = w
                
        for k in range(26):
            for i in range(26):
                for j in range(26):
                    if dist[i][k] + dist[k][j] < dist[i][j]:
                        dist[i][j] = dist[i][k] + dist[k][j]
                        
        total_cost = 0
        for s, t in zip(source, target):
            i = ord(s) - ord('a')
            j = ord(t) - ord('a')
            if dist[i][j] == float('inf'):
                return -1
            total_cost += dist[i][j]
            
        return total_cost