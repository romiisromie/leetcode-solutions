#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        if (s.empty()) return "";
        
        string t = "^";
        for (char c : s) {
            t += "#";
            t += c;
        }
        t += "#$";
        
        int n = t.length();
        vector<int> p(n, 0);
        int c = 0, r = 0;
        int max_len = 0, center_index = 0;
        
        for (int i = 1; i < n - 1; ++i) {
            int i_mirror = 2 * c - i;
            
            if (r > i) {
                p[i] = min(r - i, p[i_mirror]);
            } else {
                p[i] = 0;
            }
            
            while (t[i + 1 + p[i]] == t[i - 1 - p[i]]) {
                p[i]++;
            }
            
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
            
            if (p[i] > max_len) {
                max_len = p[i];
                center_index = i;
            }
        }
        
        int start = (center_index - 1 - max_len) / 2;
        return s.substr(start, max_len);
    }
};