from collections import defaultdict

class Solution:
    def largestOverlap(self, img1: list[list[int]], img2: list[list[int]]) -> int:
        n = len(img1)
        
        # Collect coordinates of all 1s in img1 and img2
        ones_img1 = [(r, c) for r in range(n) for c in range(n) if img1[r][c] == 1]
        ones_img2 = [(r, c) for r in range(n) for c in range(n) if img2[r][c] == 1]
        
        # Count frequency of each translation vector (dr, dc)
        vector_counts = defaultdict(int)
        max_overlap = 0
        
        for r1, c1 in ones_img1:
            for r2, c2 in ones_img2:
                dr = r2 - r1
                dc = c2 - c1
                vector_counts[(dr, dc)] += 1
                max_overlap = max(max_overlap, vector_counts[(dr, dc)])
                
        return max_overlap