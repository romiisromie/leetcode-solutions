impl Solution {
    pub fn gcd_values(nums: Vec<i32>, queries: Vec<i64>) -> Vec<i32> {
        let max_val = *nums.iter().max().unwrap_or(&0) as usize;
        
        let mut count = vec![0i64; max_val + 1];
        for &num in &nums {
            count[num as usize] += 1;
        }
        
        let mut count_multiples = vec![0i64; max_val + 1];
        for g in 1..=max_val {
            let mut c = 0;
            let mut m = g;
            while m <= max_val {
                c += count[m];
                m += g;
            }
            count_multiples[g] = c;
        }
        
        let mut gcd_pairs_count = vec![0i64; max_val + 1];
        for g in (1..=max_val).rev() {
            let total_multiples = count_multiples[g];
            let mut pairs = total_multiples * (total_multiples - 1) / 2;
            
            let mut m = 2 * g;
            while m <= max_val {
                pairs -= gcd_pairs_count[m];
                m += g;
            }
            gcd_pairs_count[g] = pairs;
        }
        
        let mut prefix_sums = vec![0i64; max_val + 1];
        for g in 1..=max_val {
            prefix_sums[g] = prefix_sums[g - 1] + gcd_pairs_count[g];
        }
        
        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let mut low = 1;
            let mut high = max_val;
            let mut target_gcd = 1;
            
            while low <= high {
                let mid = low + (high - low) / 2;
                if prefix_sums[mid] > q {
                    target_gcd = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans.push(target_gcd as i32);
        }
        
        ans
    }
}