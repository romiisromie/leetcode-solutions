// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
// 
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }

impl Solution {
    pub fn insertion_sort_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut dummy = Box::new(ListNode::new(0));
        let mut curr = head;

        while let Some(mut curr_node) = curr {
            curr = curr_node.next.take();
            
            let mut prev = &mut dummy;
            while prev.next.is_some() && prev.next.as_ref().unwrap().val < curr_node.val {
                prev = prev.next.as_mut().unwrap();
            }
            
            curr_node.next = prev.next.take();
            prev.next = Some(curr_node);
        }

        dummy.next
    }
}