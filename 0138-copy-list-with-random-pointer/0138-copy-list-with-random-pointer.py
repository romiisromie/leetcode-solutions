class Solution(object):
    def copyRandomList(self, head):
        if not head:
            return None
            
        current = head
        while current:
            new_node = Node(current.val, current.next)
            current.next = new_node
            current = new_node.next
            
        current = head
        while current:
            if current.random:
                current.next.random = current.random.next
            current = current.next.next
            
        current = head
        new_head = head.next
        while current:
            copy = current.next
            current.next = copy.next
            if copy.next:
                copy.next = copy.next.next
            current = current.next
            
        return new_head