class Solution:
    def deleteMiddle(self, head):
        if not head or not head.next:
            return None
        
        prev = None
        slow = head
        fast = head
        
        while fast and fast.next:
            prev = slow
            slow = slow.next
            fast = fast.next.next
            
        prev.next = slow.next
        
        return head
