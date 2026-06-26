#include <vector>
#include <unordered_map>
#include <algorithm>

class Solution {
public:
    int maxNumberOfFamilies(int n, std::vector<std::vector<int>>& reservedSeats) {
        std::unordered_map<int, int> rowMasks;
        
        for (const auto& seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks[row] |= (1 << (col - 2));
            }
        }
        
        long long totalGroups = 2LL * (n - rowMasks.size());
        
        int leftBlock   = 0b00001111; // seats 2,3,4,5  -> bits 0,1,2,3
        int middleBlock = 0b00111100; // seats 4,5,6,7  -> bits 2,3,4,5
        int rightBlock  = 0b11110000; // seats 6,7,8,9  -> bits 4,5,6,7
        
        for (const auto& [row, mask] : rowMasks) {
            bool leftFree = (mask & leftBlock) == 0;
            bool rightFree = (mask & rightBlock) == 0;
            
            if (leftFree && rightFree) {
                totalGroups += 2;
            } else if (leftFree || rightFree || (mask & middleBlock) == 0) {
                totalGroups += 1;
            }
        }
        
        return totalGroups;
    }
};