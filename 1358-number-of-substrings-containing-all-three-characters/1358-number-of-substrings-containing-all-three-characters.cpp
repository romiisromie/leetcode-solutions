class Solution {
public:
    int numberOfSubstrings(string s) {
        int lastSeen[3] = {-1, -1, -1};
        int ans = 0;
        int n = s.length();
        
        for (int i = 0; i < n; ++i) {
            lastSeen[s[i] - 'a'] = i;
            ans += min({lastSeen[0], lastSeen[1], lastSeen[2]}) + 1;
        }
        
        return ans;
    }
};