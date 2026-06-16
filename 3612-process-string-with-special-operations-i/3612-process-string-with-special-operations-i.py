class Solution(object):
    def processStr(self, s):
        """
        :type s: str
        :rtype: str
        """
        res = []
        for char in s:
            if char == '*':
                if res:
                    res.pop()
            elif char == '#':
                res = res + res
            elif char == '%':
                res.reverse()
            else:
                res.append(char)
        return "".join(res)