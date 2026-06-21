class Solution(object):
    def buildArray(self, target, n):
        operations = []
        idx = 0
        target_len = len(target)
        
        for num in range(1, n + 1):
            if idx == target_len:
                break
                
            operations.append("Push")
            if num == target[idx]:
                idx += 1
            else:
                operations.append("Pop")
                
        return operations