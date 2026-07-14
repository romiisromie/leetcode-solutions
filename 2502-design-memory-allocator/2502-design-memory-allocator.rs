struct Allocator {
    memory: Vec<i32>,
    n: usize,
}

impl Allocator {
    fn new(n: i32) -> Self {
        Self {
            memory: vec![0; n as usize],
            n: n as usize,
        }
    }
    
    fn allocate(&mut self, size: i32, m_id: i32) -> i32 {
        let size = size as usize;
        let mut count = 0;
        
        for i in 0..self.n {
            if self.memory[i] == 0 {
                count += 1;
                if count == size {
                    let start_idx = i + 1 - size;
                    for j in start_idx..=i {
                        self.memory[j] = m_id;
                    }
                    return start_idx as i32;
                }
            } else {
                count = 0;
            }
        }
        
        -1
    }
    
    fn free_memory(&mut self, m_id: i32) -> i32 {
        let mut freed_count = 0;
        
        for i in 0..self.n {
            if self.memory[i] == m_id {
                self.memory[i] = 0;
                freed_count += 1;
            }
        }
        
        freed_count
    }
}