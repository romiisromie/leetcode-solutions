impl Solution {
    pub fn has_all_codes(s: String, k: i32) -> bool {
        let k = k as usize;
        if s.len() < k {
            return false;
        }

        let total_codes = 1 << k;
        let mut seen = vec![false; total_codes];
        let mut count = 0;
        
        let mask = total_codes - 1;
        let mut current_hash = 0;
        let bytes = s.as_bytes();

        for i in 0..k {
            current_hash = (current_hash << 1) | (bytes[i] - b'0') as usize;
        }
        seen[current_hash] = true;
        count += 1;

        for i in k..bytes.len() {
            current_hash = ((current_hash << 1) & mask) | (bytes[i] - b'0') as usize;
            if !seen[current_hash] {
                seen[current_hash] = true;
                count += 1;
                if count == total_codes {
                    return true;
                }
            }
        }

        count == total_codes
    }
}