class Solution(object):
    def countStudents(self, students, sandwiches):
        count = [students.count(0), students.count(1)]
        
        for sandwich in sandwiches:
            if count[sandwich] > 0:
                count[sandwich] -= 1
            else:
                break
                
        return sum(count)