#include <string>
#include <stack>

class Solution {
public:
    std::string minRemoveToMakeValid(std::string s) {
        std::stack<int> st;
        
        for (int i = 0; i < s.length(); ++i) {
            if (s[i] == '(') {
                st.push(i);
            } else if (s[i] == ')') {
                if (!st.empty()) {
                    st.pop();
                } else {
                    s[i] = '*';
                }
            }
        }
        
        while (!st.empty()) {
            s[st.top()] = '*';
            st.pop();
        }
        
        std::string result = "";
        for (char c : s) {
            if (c != '*') {
                result += c;
            }
        }
        
        return result;
    }
};