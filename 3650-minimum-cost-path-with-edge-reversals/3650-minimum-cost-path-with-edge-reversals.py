import heapq
from collections import defaultdict

class Solution(object):
    def minCost(self, n, edges):
        graph = defaultdict(list)
        for u, v, w in edges:
            graph[u].append((v, w))
            graph[v].append((u, 2 * w))
            
        pq = [(0, 0)]
        dist = {0: 0}
        
        while pq:
            cost, u = heapq.heappop(pq)
            
            if cost > dist[u]:
                continue
                
            if u == n - 1:
                return cost
                
            for v, w in graph[u]:
                if cost + w < dist.get(v, float('inf')):
                    dist[v] = cost + w
                    heapq.heappush(pq, (cost + w, v))
                    
        return dist.get(n - 1, -1)