impl Solution {
    pub fn shift_grid(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let m = grid.len();
        let n = grid[0].len();
        let total_elements = m * n;
        let k = (k as usize) % total_elements;
        
        let mut result = vec![vec![0; n]; m];
        
        for i in 0..m {
            for j in 0..n {
                let flat_idx = i * n + j;
                let new_flat_idx = (flat_idx + k) % total_elements;
                result[new_flat_idx / n][new_flat_idx % n] = grid[i][j];
            }
        }
        
        result
    }
}