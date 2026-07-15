impl Solution {
    pub fn max_distance(side: i32, points: Vec<Vec<i32>>, k: i32) -> i32 {
        let s = side as i64;
        let k = k as usize;
        let mut p_1d = Vec::with_capacity(points.len());

        for p in points {
            let x = p[0] as i64;
            let y = p[1] as i64;
            let d = if y == 0 {
                x
            } else if x == s {
                s + y
            } else if y == s {
                3 * s - x
            } else {
                4 * s - y
            };
            p_1d.push(d);
        }

        p_1d.sort_unstable();
        let n = p_1d.len();

        let mut extended = vec![0i64; 2 * n];
        for i in 0..n {
            extended[i] = p_1d[i];
            extended[i + n] = p_1d[i] + 4 * s;
        }

        let mut low = 1i64;
        let mut high = 2 * s;
        let mut ans = 0;

        let mut next_idx = vec![0; 2 * n];

        while low <= high {
            let mid = low + (high - low) / 2;
            
            let mut j = 0;
            for i in 0..(2 * n) {
                while j < 2 * n && extended[j] < extended[i] + mid {
                    j += 1;
                }
                next_idx[i] = j;
            }

            let mut possible = false;
            for i in 0..n {
                let mut curr = i;
                let mut valid = true;
                for _ in 0..(k - 1) {
                    curr = next_idx[curr];
                    if curr >= 2 * n {
                        valid = false;
                        break;
                    }
                }
                if valid && extended[i + n] >= extended[curr] + mid {
                    possible = true;
                    break;
                }
            }

            if possible {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        ans as i32
    }
}