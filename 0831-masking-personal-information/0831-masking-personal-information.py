class Solution(object):
    def maskPII(self, s):
        if '@' in s:
            s = s.lower()
            name, domain = s.split('@')
            return name[0] + "*****" + name[-1] + "@" + domain
        else:
            digits = "".join(c for c in s if c.isdigit())
            local = "***-***-" + digits[-4:]
            
            if len(digits) == 10:
                return local
            elif len(digits) == 11:
                return "+*-" + local
            elif len(digits) == 12:
                return "+**-" + local
            else:
                return "+***-" + local