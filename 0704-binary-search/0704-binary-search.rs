impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let mut low = 0;
        let mut high = nums.len() as i32 - 1;

        while low <= high {
            let mid = low + (high - low) / 2;
            let mid_val = nums[mid as usize];

            if mid_val == target {
                return mid;
            } else if mid_val < target {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        -1
    }
}