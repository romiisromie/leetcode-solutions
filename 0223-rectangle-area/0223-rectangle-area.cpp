#include <algorithm>

using namespace std;

class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        long long area1 = (long long)(ax2 - ax1) * (ay2 - ay1);
        long long area2 = (long long)(bx2 - bx1) * (by2 - by1);

        long long overlap_width = min(ax2, bx2) - max(ax1, bx1);
        long long overlap_height = min(ay2, by2) - max(ay1, by1);

        long long overlap_area = 0;
        if (overlap_width > 0 && overlap_height > 0) {
            overlap_area = overlap_width * overlap_height;
        }

        return area1 + area2 - overlap_area;
    }
};