import heapq
from collections import defaultdict

class Solution(object):
    def minimumCost(self, source, target, original, changed, cost):
        graph = defaultdict(list)
        for u, v, w in zip(original, changed, cost):
            graph[u].append((v, w))
            
        distinct_words = set(original)
        shortest_paths = {}
        
        for start in distinct_words:
            dist = {start: 0}
            pq = [(0, start)]
            while pq:
                d, node = heapq.heappop(pq)
                if d > dist[node]:
                    continue
                for neighbor, weight in graph[node]:
                    if d + weight < dist.get(neighbor, float('inf')):
                        dist[neighbor] = d + weight
                        heapq.heappush(pq, (d + weight, neighbor))
            
            for end, d in dist.items():
                if start != end:
                    shortest_paths[(start, end)] = d
                    
        lengths = sorted(list(set(len(w) for w in original)), reverse=True)
        
        n = len(source)
        dp = [float('inf')] * (n + 1)
        dp[0] = 0
        
        for i in range(n):
            if dp[i] == float('inf'):
                continue
                
            if source[i] == target[i]:
                if dp[i] < dp[i + 1]:
                    dp[i + 1] = dp[i]
                    
            for l in lengths:
                if i + l <= n:
                    sub_s = source[i:i+l]
                    sub_t = target[i:i+l]
                    
                    if sub_s == sub_t:
                        if dp[i] < dp[i + l]:
                            dp[i + l] = dp[i]
                    elif (sub_s, sub_t) in shortest_paths:
                        w = shortest_paths[(sub_s, sub_t)]
                        if dp[i] + w < dp[i + l]:
                            dp[i + l] = dp[i] + w
                            
        return dp[n] if dp[n] != float('inf') else -1