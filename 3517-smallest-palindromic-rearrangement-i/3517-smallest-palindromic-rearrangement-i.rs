impl Solution {
    pub fn smallest_palindrome(s: String) -> String {
        let mut counts = [0; 26];
        for byte in s.bytes() {
            counts[(byte - b'a') as usize] += 1;
        }

        let mut half = Vec::with_capacity(s.len() / 2);
        let mut middle = None;

        for i in 0..26 {
            if counts[i] % 2 != 0 {
                middle = Some((b'a' + i as u8) as char);
            }
            for _ in 0..(counts[i] / 2) {
                half.push((b'a' + i as u8) as char);
            }
        }

        let first_half: String = half.iter().collect();
        let second_half: String = half.iter().rev().collect();

        if let Some(m) = middle {
            format!("{}{}{}", first_half, m, second_half)
        } else {
            format!("{}{}", first_half, second_half)
        }
    }
}