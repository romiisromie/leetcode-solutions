class Node(object):
    def __init__(self, key, value):
        self.key = key
        self.val = value
        self.freq = 1
        self.prev = None
        self.next = None

class DoublyLinkedList(object):
    def __init__(self):
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.size = 0

    def add_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node
        self.size += 1

    def remove(self, node):
        prev_node = node.prev
        next_node = node.next
        prev_node.next = next_node
        next_node.prev = prev_node
        self.size -= 1

    def remove_tail(self):
        if self.size == 0:
            return None
        lru_node = self.tail.prev
        self.remove(lru_node)
        return lru_node

class LFUCache(object):
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = {}
        self.freq_map = {}
        self.min_freq = 0

    def _update(self, node):
        freq = node.freq
        self.freq_map[freq].remove(node)
        
        if self.freq_map[freq].size == 0:
            del self.freq_map[freq]
            if self.min_freq == freq:
                self.min_freq += 1
                
        node.freq += 1
        new_freq = node.freq
        if new_freq not in self.freq_map:
            self.freq_map[new_freq] = DoublyLinkedList()
        self.freq_map[new_freq].add_to_head(node)

    def get(self, key):
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self._update(node)
        return node.val

    def put(self, key, value):
        if self.capacity <= 0:
            return

        if key in self.cache:
            node = self.cache[key]
            node.val = value
            self._update(node)
        else:
            if len(self.cache) >= self.capacity:
                lru_list = self.freq_map[self.min_freq]
                evicted_node = lru_list.remove_tail()
                if evicted_node:
                    del self.cache[evicted_node.key]
                    if lru_list.size == 0:
                        del self.freq_map[self.min_freq]
                        
            new_node = Node(key, value)
            self.cache[key] = new_node
            self.min_freq = 1
            if 1 not in self.freq_map:
                self.freq_map[1] = DoublyLinkedList()
            self.freq_map[1].add_to_head(new_node)
