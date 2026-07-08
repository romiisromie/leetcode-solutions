#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int longestBalanced(string s) {
        int n = s.length();
        int max_len = 0;

        for (int mask = 1; mask < 8; ++mask) {
            bool hasA = (mask & 1);
            bool hasB = (mask & 2);
            bool hasC = (mask & 4);

            unordered_map<long long, int> first_occ;
            long long initial_key = ((0LL + n) << 32) | (0 + n);
            first_occ[initial_key] = 0;

            int countA = 0, countB = 0, countC = 0;
            int lastA = -1, lastB = -1, lastC = -1;

            for (int r = 0; r < n; ++r) {
                if (s[r] == 'a') { countA++; lastA = r; }
                else if (s[r] == 'b') { countB++; lastB = r; }
                else if (s[r] == 'c') { countC++; lastC = r; }

                long long diff1 = 0, diff2 = 0;
                if (hasA && hasB && hasC) {
                    diff1 = countA - countB;
                    diff2 = countB - countC;
                } else if (hasA && hasB) {
                    diff1 = countA - countB;
                    diff2 = countC; 
                } else if (hasA && hasC) {
                    diff1 = countA - countC;
                    diff2 = countB;
                } else if (hasB && hasC) {
                    diff1 = countB - countC;
                    diff2 = countA;
                } else if (hasA) {
                    diff1 = countB;
                    diff2 = countC;
                } else if (hasB) {
                    diff1 = countA;
                    diff2 = countC;
                } else if (hasC) {
                    diff1 = countA;
                    diff2 = countB;
                }

                long long key = ((diff1 + n) << 32) | (diff2 + n);

                if (first_occ.count(key)) {
                    int l = first_occ[key];
                    
                    int curA = (lastA >= l) ? 1 : 0;
                    int curB = (lastB >= l) ? 1 : 0;
                    int curC = (lastC >= l) ? 1 : 0;

                    bool valid = true;
                    if (hasA && !curA) valid = false;
                    if (!hasA && curA) valid = false;
                    if (hasB && !curB) valid = false;
                    if (!hasB && curB) valid = false;
                    if (hasC && !curC) valid = false;
                    if (!hasC && curC) valid = false;

                    if (valid) {
                        max_len = max(max_len, r + 1 - l);
                    }
                } else {
                    first_occ[key] = r + 1;
                }
            }
        }

        return max_len;
    }
};