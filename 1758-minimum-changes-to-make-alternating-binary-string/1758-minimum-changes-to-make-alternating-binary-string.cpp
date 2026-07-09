class Solution {
public:
    int minOperations(string s) {
        int count0 = 0;
        int n = s.length();
        
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                if (s[i] != '0') count0++;
            } else {
                if (s[i] != '1') count0++;
            }
        }
        
        return min(count0, n - count0);
    }
};