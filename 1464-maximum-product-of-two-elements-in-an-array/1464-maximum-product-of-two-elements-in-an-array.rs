impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut max1 = 0;
        let mut max2 = 0;

        for n in nums {
            if n > max1 {
                max2 = max1;
                max1 = n;
            } else if n > max2 {
                max2 = n;
            }
        }

        (max1 - 1) * (max2 - 1)
    }
}