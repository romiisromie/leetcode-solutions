#include <vector>
#include <stack>

class Solution {
public:
    std::vector<int> canSeePersonsCount(std::vector<int>& heights) {
        int n = heights.size();
        std::vector<int> result(n, 0);
        std::stack<int> st;

        for (int i = n - 1; i >= 0; --i) {
            int count = 0;
            while (!st.empty() && heights[i] > st.top()) {
                st.pop();
                count++;
            }
            if (!st.empty()) {
                count++;
            }
            result[i] = count;
            st.push(heights[i]);
        }

        return result;
    }
};