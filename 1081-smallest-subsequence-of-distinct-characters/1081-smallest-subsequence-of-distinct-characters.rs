impl Solution {
    pub fn smallest_subsequence(s: String) -> String {
        let mut last_idx = [0; 26];
        let bytes = s.as_bytes();
        for (i, &c) in bytes.iter().enumerate() {
            last_idx[(c - b'a') as usize] = i;
        }

        let mut stack = Vec::new();
        let mut visited = [false; 26];

        for (i, &c) in bytes.iter().enumerate() {
            let char_idx = (c - b'a') as usize;
            if visited[char_idx] {
                continue;
            }

            while let Some(&top) = stack.last() {
                let top_idx = (top - b'a') as usize;
                if top > c && last_idx[top_idx] > i {
                    stack.pop();
                    visited[top_idx] = false;
                } else {
                    break;
                }
            }

            stack.push(c);
            visited[char_idx] = true;
        }

        String::from_utf8(stack).unwrap()
    }
}