class Solution(object):
    def exclusiveTime(self, n, logs):
        exclusive_time = [0] * n
        stack = []
        prev_time = 0
        
        for log in logs:
            parts = log.split(':')
            fid = int(parts[0])
            typ = parts[1]
            timestamp = int(parts[2])
            
            if typ == "start":
                if stack:
                    exclusive_time[stack[-1]] += timestamp - prev_time
                stack.append(fid)
                prev_time = timestamp
            else:
                exclusive_time[stack.pop()] += timestamp - prev_time + 1
                prev_time = timestamp + 1
                
        return exclusive_time