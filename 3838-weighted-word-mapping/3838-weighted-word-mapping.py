class Solution(object):
    def mapWordWeights(self, words, weights):
        """
        :type words: List[str]
        :type weights: List[int]
        :rtype: str
        """
        ans = []
        alphabet = 'zyxwvutsrqponmlkjihgfedcba'
        
        for word in words:
            word_weight = 0
            for char in word:
                index = ord(char) - ord('a')
                word_weight += weights[index]
                
            modulo_result = word_weight % 26
            mapped_char = alphabet[modulo_result]
            ans.append(mapped_char)
            
        return "".join(ans)
