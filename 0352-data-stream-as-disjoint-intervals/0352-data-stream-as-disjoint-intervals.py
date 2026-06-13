class SummaryRanges(object):

    def __init__(self):
        self.seen = [False] * 10002

    def addNum(self, value):
        """
        :type value: int
        :rtype: None
        """
        self.seen[value] = True

    def getIntervals(self):
        """
        :rtype: List[List[int]]
        """
        intervals = []
        start = -1
        
        for i in range(10002):
            if self.seen[i]:
                if start == -1:
                    start = i
            else:
                if start != -1:
                    intervals.append([start, i - 1])
                    start = -1
                    
        return intervals