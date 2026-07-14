impl Solution {
    pub fn subsequence_pair_count(nums: Vec<i32>) -> i32 {
        fn gcd(a: i32, b: i32) -> i32 {
            if b == 0 { a } else { gcd(b, a % b) }
        }
        
        let n = nums.len();
        let mut memo = vec![vec![vec![-1; 201]; 201]; n];
        
        fn dp(nums: &Vec<i32>, i: usize, g1: i32, g2: i32, n: usize, memo: &mut Vec<Vec<Vec<i32>>>) -> i32 {
            if i == n {
                return if g1 > 0 && g2 > 0 && g1 == g2 { 1 } else { 0 };
            }
            
            if memo[i][g1 as usize][g2 as usize] != -1 {
                return memo[i][g1 as usize][g2 as usize];
            }
            
            let mut res = dp(nums, i + 1, g1, g2, n, memo);
            res = (res + dp(nums, i + 1, if g1 == 0 { nums[i] } else { gcd(g1, nums[i]) }, g2, n, memo)) % 1_000_000_007;
            res = (res + dp(nums, i + 1, g1, if g2 == 0 { nums[i] } else { gcd(g2, nums[i]) }, n, memo)) % 1_000_000_007;
            
            memo[i][g1 as usize][g2 as usize] = res;
            res
        }
        
        dp(&nums, 0, 0, 0, n, &mut memo)
    }
}