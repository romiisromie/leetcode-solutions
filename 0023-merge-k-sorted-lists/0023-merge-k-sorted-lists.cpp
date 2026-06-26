#include <vector>
#include <queue>

class Solution {
private:
    struct compare {
        bool operator()(ListNode* a, ListNode* b) {
            return a->val > b->val;
        }
    };

public:
    ListNode* mergeKLists(std::vector<ListNode*>& lists) {
        std::priority_queue<ListNode*, std::vector<ListNode*>, compare> pq;
        
        for (ListNode* list : lists) {
            if (list) {
                pq.push(list);
            }
        }
        
        ListNode dummy(0);
        ListNode* tail = &dummy;
        
        while (!pq.empty()) {
            ListNode* curr = pq.top();
            pq.pop();
            
            tail->next = curr;
            tail = tail->next;
            
            if (curr->next) {
                pq.push(curr->next);
            }
        }
        
        return dummy.next;
    }
};