impl Solution {
    pub fn max_active_sections_after_trade(s: String) -> i32 {
        let mut t = String::with_capacity(s.len() + 2);
        t.push('1');
        t.push_str(&s);
        t.push('1');

        let chars: Vec<char> = t.chars().collect();
        let n = chars.len();
        
        let mut groups = Vec::new();
        let mut i = 0;
        
        while i < n {
            let ch = chars[i];
            let mut count = 0;
            while i < n && chars[i] == ch {
                count += 1;
                i += 1;
            }
            groups.push((ch, count));
        }

        let m = groups.len();
        let mut max_gain = 0;

        for idx in 0..m {
            if groups[idx].0 == '1' && idx >= 2 && idx + 2 < m {
                if groups[idx - 1].0 == '0' && groups[idx + 1].0 == '0' {
                    let left_zeros = groups[idx - 1].1;
                    let right_zeros = groups[idx + 1].1;
                    let gain = left_zeros + right_zeros;
                    if gain > max_gain {
                        max_gain = gain;
                    }
                }
            }
        }

        let mut total_ones = 0;
        for c in s.chars() {
            if c == '1' {
                total_ones += 1;
            }
        }

        total_ones + max_gain
    }
}