class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[] freq = new int[128];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                freq[board[i][j]]++;
            }
        }
        char[] w = word.toCharArray();
        for (char c : w) {
            if (--freq[c] < 0) {
                return false;
            }
        }
        if (countChar(board, w[0]) > countChar(board, w[w.length - 1])) {
            for (int i = 0, j = w.length - 1; i < j; i++, j--) {
                char tmp = w[i];
                w[i] = w[j];
                w[j] = tmp;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, w, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int countChar(char[][] board, char target) {
        int count = 0;
        for (char[] row : board) {
            for (char c : row) {
                if (c == target) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(char[][] board, char[] word, int r, int c, int idx) {
        if (idx == word.length) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word[idx]) {
            return false;
        }
        char temp = board[r][c];
        board[r][c] = '#';
        boolean found = dfs(board, word, r + 1, c, idx + 1)
                || dfs(board, word, r - 1, c, idx + 1)
                || dfs(board, word, r, c + 1, idx + 1)
                || dfs(board, word, r, c - 1, idx + 1);
        board[r][c] = temp;
        return found;
    }
}