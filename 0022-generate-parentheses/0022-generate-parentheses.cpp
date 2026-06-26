#include <vector>
#include <string>

class Solution {
private:
    void backtrack(std::vector<std::string>& result, std::string current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            result.push_back(current);
            return;
        }

        if (open < n) {
            backtrack(result, current + "(", open + 1, close, n);
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, n);
        }
    }

public:
    std::vector<std::string> generateParenthesis(int n) {
        std::vector<std::string> result;
        backtrack(result, "", 0, 0, n);
        return result;
    }
};