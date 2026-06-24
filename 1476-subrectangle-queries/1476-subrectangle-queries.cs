public class SubrectangleQueries {
    private int[][] grid;

    public SubrectangleQueries(int[][] rectangle) {
        grid = rectangle;
    }
    
    public void UpdateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int r = row1; r <= row2; r++) {
            for (int c = col1; c <= col2; c++) {
                grid[r][c] = newValue;
            }
        }
    }
    
    public int GetValue(int row, int col) {
        return grid[row][col];
    }
}