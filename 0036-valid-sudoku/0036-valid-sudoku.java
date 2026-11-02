import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];
                
                if (currentVal != '.') {
                    if (!seen.add(currentVal + " in row " + i) ||
                        !seen.add(currentVal + " in col " + j) ||
                        !seen.add(currentVal + " in box " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}