#include <string>
#include <vector>
#include <algorithm>

class Solution {
public:
    int longestBalanced(std::string s) {
        int n = s.length();
        int max_len = 0;

        for (int i = 0; i < n; ++i) {
            std::vector<int> counts(26, 0);
            
            for (int j = i; j < n; ++j) {
                counts[s[j] - 'a']++;
                
                int target_count = -1;
                bool is_balanced = true;
                
                for (int k = 0; k < 26; ++k) {
                    if (counts[k] > 0) {
                        if (target_count == -1) {
                            target_count = counts[k];
                        } else if (counts[k] != target_count) {
                            is_balanced = false;
                            break;
                        }
                    }
                }
                
                if (is_balanced) {
                    max_len = std::max(max_len, j - i + 1);
                }
            }
        }

        return max_len;
    }
};