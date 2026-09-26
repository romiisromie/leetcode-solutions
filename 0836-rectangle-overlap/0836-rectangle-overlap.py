class Solution:
    def isRectangleOverlap(self, rec1: list[int], rec2: list[int]) -> bool:
        # Destructure coordinates for clarity
        x1, y1, x2, y2 = rec1
        x3, y3, x4, y4 = rec2

        # Check if overlaps exist on both X and Y axes
        x_overlap = x1 < x4 and x3 < x2
        y_overlap = y1 < y4 and y3 < y2

        return x_overlap and y_overlap