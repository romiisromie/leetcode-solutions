from collections import deque
from typing import List

class Solution:
    def remainingMethods(self, n: int, k: int, invocations: List[List[int]]) -> List[int]:
        # Step 1: Build adjacency list for invocation graph
        adj = [[] for _ in range(n)]
        for u, v in invocations:
            adj[u].append(v)
            
        # Step 2: BFS to find all methods reachable from k (suspicious set)
        suspicious = set()
        queue = deque([k])
        suspicious.add(k)
        
        while queue:
            curr = queue.popleft()
            for neighbor in adj[curr]:
                if neighbor not in suspicious:
                    suspicious.add(neighbor)
                    queue.append(neighbor)
                    
        # Step 3: Check if any non-suspicious method calls a suspicious method
        for u, v in invocations:
            if u not in suspicious and v in suspicious:
                # Removal invalid: return all methods
                return list(range(n))
                
        # Step 4: Return remaining non-suspicious methods
        return [i for i in range(n) if i not in suspicious]