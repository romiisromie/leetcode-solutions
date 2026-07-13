impl Solution {
    pub fn max_sum_trionic(nums: Vec<i32>) -> i64 {
        let n = nums.len();
        if n < 4 {
            return 0;
        }

        let inf = 1e18 as i64;
        let mut dp1 = vec![-inf; n];
        let mut dp2 = vec![-inf; n];
        let mut dp3 = vec![-inf; n];

        let mut max_total_sum = -inf;

        for i in 1..n {
            let val = nums[i] as i64;
            let prev_val = nums[i - 1] as i64;

            if val > prev_val {
                dp1[i] = val + prev_val.max(dp1[i - 1]);

                if dp2[i - 1] != -inf || dp3[i - 1] != -inf {
                    dp3[i] = val + dp2[i - 1].max(dp3[i - 1]);
                }
            } else if val < prev_val {
                if dp1[i - 1] != -inf || dp2[i - 1] != -inf {
                    dp2[i] = val + dp1[i - 1].max(dp2[i - 1]);
                }
            }

            max_total_sum = max_total_sum.max(dp3[i]);
        }

        max_total_sum
    }
}