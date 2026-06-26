#include <vector>
#include <stack>
#include <algorithm>

class Solution {
private:
    int leakLargestRectangleArea(std::vector<int>& heights) {
        std::stack<int> st;
        int max_area = 0;
        int n = heights.size();
        
        for (int i = 0; i <= n; ++i) {
            int current_height = (i == n) ? 0 : heights[i];
            while (!st.empty() && current_height < heights[st.top()]) {
                int h = heights[st.top()];
                st.pop();
                int w = st.empty() ? i : i - st.top() - 1;
                max_area = std::max(max_area, h * w);
            }
            st.push(i);
        }
        return max_area;
    }

public:
    int maximalRectangle(std::vector<std::vector<char>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return 0;
        
        int rows = matrix.size();
        int cols = matrix[0].size();
        std::vector<int> heights(cols, 0);
        int max_rectangle = 0;
        
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            max_rectangle = std::max(max_rectangle, leakLargestRectangleArea(heights));
        }
        
        return max_rectangle;
    }
};