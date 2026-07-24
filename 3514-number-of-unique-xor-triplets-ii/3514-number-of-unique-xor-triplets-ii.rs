use std::collections::HashSet;

impl Solution {
    pub fn unique_xor_triplets(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut seen = HashSet::new();
        let mut pair_xor = HashSet::new();
        
        for j in 0..n {
            for k in j..n {
                pair_xor.insert(nums[j] ^ nums[k]);
            }
        }
        
        for i in 0..n {
            for &p in &pair_xor {
                seen.insert(nums[i] ^ p);
            }
        }
        
        seen.len() as i32
    }
}