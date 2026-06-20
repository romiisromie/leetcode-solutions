class Solution(object):
    def maxBuilding(self, n, restrictions):
        r = restrictions
        r.append([1, 0])
        r.append([n, n - 1])
        r.sort()
        
        m = len(r)
        
        for i in range(1, m):
            id_diff = r[i][0] - r[i-1][0]
            r[i][1] = min(r[i][1], r[i-1][1] + id_diff)
            
        for i in range(m - 2, -1, -1):
            id_diff = r[i+1][0] - r[i][0]
            r[i][1] = min(r[i][1], r[i+1][1] + id_diff)
            
        ans = 0
        for i in range(m - 1):
            id1, h1 = r[i]
            id2, h2 = r[i+1]
            peak = (h1 + h2 + (id2 - id1)) // 2
            if peak > ans:
                ans = peak
                
        return ans