#include <string>
#include <vector>

class Solution {
public:
    std::string removeDuplicates(std::string s, int k) {
        std::vector<std::pair<char, int>> st;
        
        for (char c : s) {
            if (!st.empty() && st.back().first == c) {
                st.back().second++;
                if (st.back().second == k) {
                    st.pop_back();
                }
            } else {
                st.push_back({c, 1});
            }
        }
        
        std::string result = "";
        for (const auto& p : st) {
            result.append(p.second, p.first);
        }
        
        return result;
    }
};