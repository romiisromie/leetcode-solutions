impl Solution {
    pub fn sequential_digits(low: i32, high: i32) -> Vec<i32> {
        let mut result = Vec::new();
        let digits = "123456789";

        for length in 2..=9 {
            for start in 0..=(9 - length) {
                if let Some(sub_str) = digits.get(start..(start + length)) {
                    if let Ok(num) = sub_str.parse::<i32>() {
                        if num >= low && num <= high {
                            result.push(num);
                        }
                    }
                }
            }
        }

        result
    }
}