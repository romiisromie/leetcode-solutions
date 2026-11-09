import heapq

class Solution:
    def assignTasks(self, servers: list[int], tasks: list[int]) -> list[int]:
        # Initialize free servers min-heap with (weight, index)
        free_servers = [(weight, i) for i, weight in enumerate(servers)]
        heapq.heapify(free_servers)
        
        # busy_servers stores (free_time, weight, index)
        busy_servers = []
        ans = [0] * len(tasks)
        current_time = 0
        
        for j, task_time in enumerate(tasks):
            current_time = max(current_time, j)
            
            # If no free servers are available, fast-forward time to the next available server
            if not free_servers and busy_servers[0][0] > current_time:
                current_time = busy_servers[0][0]
                
            # Release all servers that finish their tasks at or before current_time
            while busy_servers and busy_servers[0][0] <= current_time:
                free_time, weight, index = heapq.heappop(busy_servers)
                heapq.heappush(free_servers, (weight, index))
                
            # Assign the task to the highest-priority free server
            weight, index = heapq.heappop(free_servers)
            ans[j] = index
            
            # Push the server into the busy heap with its new completion time
            heapq.heappush(busy_servers, (current_time + task_time, weight, index))
            
        return ans