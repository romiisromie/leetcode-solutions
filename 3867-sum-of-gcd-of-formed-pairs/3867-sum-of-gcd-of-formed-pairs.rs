impl Solution {
    pub fn gcd_sum(nums: Vec<i32>) -> i64 {
        fn gcd(a: i32, b: i32) -> i32 {
            if b == 0 { a } else { gcd(b, a % b) }
        }

        let mut max_val = 0;
        let mut prefix_gcd = Vec::with_capacity(nums.len());

        for &num in &nums {
            if num > max_val {
                max_val = num;
            }
            prefix_gcd.push(gcd(num, max_val));
        }

        prefix_gcd.sort_unstable();

        let mut sum = 0i64;
        let mut left = 0;
        let mut right = prefix_gcd.len() - 1;

        while left < right {
            sum += gcd(prefix_gcd[left], prefix_gcd[right]) as i64;
            left += 1;
            right -= 1;
        }

        sum
    }
}