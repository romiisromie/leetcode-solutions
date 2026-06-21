class Solution(object):
    def licenseKeyFormatting(self, s, k):
        s = s.replace('-', '').upper()
        
        n = len(s)
        first_group_len = n % k
        
        res = []
        if first_group_len > 0:
            res.append(s[:first_group_len])
            
        for i in range(first_group_len, n, k):
            res.append(s[i:i+k])
            
        return "-".join(res)