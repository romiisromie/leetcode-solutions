impl Solution {
    pub fn maximum_product(nums: Vec<i32>) -> i32 {
        let mut max1 = i32::MIN;
        let mut max2 = i32::MIN;
        let mut max3 = i32::MIN;
        let mut min1 = i32::MAX;
        let mut min2 = i32::MAX;

        for n in nums {
            if n > max1 {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if n > max2 {
                max3 = max2;
                max2 = n;
            } else if n > max3 {
                max3 = n;
            }

            if n < min1 {
                min2 = min1;
                min1 = n;
            } else if n < min2 {
                min2 = n;
            }
        }

        (max1 * max2 * max3).max(max1 * min1 * min2)
    }
}