class Solution {
    public int numberOfSubstrings(String s) {
        int[] lastPos = {-1, -1, -1};
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            lastPos[s.charAt(i) - 'a'] = i;
            
            if (lastPos[0] != -1 && lastPos[1] != -1 && lastPos[2] != -1) {
                int minIdx = Math.min(lastPos[0], Math.min(lastPos[1], lastPos[2]));
                count += minIdx + 1;
            }
        }
        
        return count;
    }
}
