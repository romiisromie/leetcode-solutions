class StreamChecker(object):

    def __init__(self, words):
        """
        :type words: List[str]
        """
        self.trie = {}
        self.stream = []
        self.max_len = 0
        
        for word in words:
            self.max_len = max(self.max_len, len(word))
            node = self.trie
            for char in reversed(word):
                if char not in node:
                    node[char] = {}
                node = node[char]
            node['$'] = True

    def query(self, letter):
        """
        :type letter: str
        :rtype: bool
        """
        self.stream.append(letter)
        
        if len(self.stream) > self.max_len:
            self.stream.pop(0)
            
        node = self.trie
        for i in range(len(self.stream) - 1, -1, -1):
            char = self.stream[i]
            if char not in node:
                return False
            node = node[char]
            if '$' in node:
                return True
        return False