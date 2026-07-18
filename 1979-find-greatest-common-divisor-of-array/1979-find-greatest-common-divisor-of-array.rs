impl Solution {
    pub fn find_gcd(nums: Vec<i32>) -> i32 {
        let mut min_val = nums[0];
        let mut max_val = nums[0];

        for &num in &nums {
            if num < min_val {
                min_val = num;
            }
            if num > max_val {
                max_val = num;
            }
        }

        fn gcd(a: i32, b: i32) -> i32 {
            if b == 0 { a } else { gcd(b, a % b) }
        }

        gcd(min_val, max_val)
    }
}