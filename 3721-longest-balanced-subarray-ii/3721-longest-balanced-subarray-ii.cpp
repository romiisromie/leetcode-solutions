#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

class SegmentTree {
private:
    int n;
    vector<int> tree_min;
    vector<int> tree_max;
    vector<int> lazy;

    void push(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree_min[node] += lazy[node];
            tree_max[node] += lazy[node];
            if (start != end) {
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    void update(int node, int start, int end, int l, int r, int val) {
        push(node, start, end);
        if (start > end || start > r || end < l) return;

        if (start >= l && end <= r) {
            lazy[node] += val;
            push(node, start, end);
            return;
        }

        int mid = start + (end - start) / 2;
        update(2 * node, start, mid, l, r, val);
        update(2 * node + 1, mid + 1, end, l, r, val);
        tree_min[node] = min(tree_min[2 * node], tree_min[2 * node + 1]);
        tree_max[node] = max(tree_max[2 * node], tree_max[2 * node + 1]);
    }

    int query(int node, int start, int end, int l, int r, int val) {
        push(node, start, end);
        if (start > end || start > r || end < l || tree_min[node] > val || tree_max[node] < val) {
            return -1;
        }

        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;
        int left_res = query(2 * node, start, mid, l, r, val);
        if (left_res != -1) return left_res;
        return query(2 * node + 1, mid + 1, end, l, r, val);
    }

public:
    SegmentTree(int size) {
        n = size;
        tree_min.assign(4 * n, 0);
        tree_max.assign(4 * n, 0);
        lazy.assign(4 * n, 0);
    }

    void range_add(int l, int r, int val) {
        if (l <= r) update(1, 0, n - 1, l, r, val);
    }

    int find_leftmost(int l, int r, int val) {
        if (l > r) return -1;
        return query(1, 0, n - 1, l, r, val);
    }
};

class Solution {
public:
    int longestBalanced(vector<int>& nums) {
        int n = nums.size();
        SegmentTree st(n);
        unordered_map<int, int> last_pos;
        int max_len = 0;

        for (int r = 0; r < n; ++r) {
            int val = nums[r];
            int sign = (val % 2 == 0) ? 1 : -1;
            int prev = last_pos.count(val) ? last_pos[val] : -1;

            st.range_add(prev + 1, r, sign);
            last_pos[val] = r;

            int l = st.find_leftmost(0, r, 0);
            if (l != -1) {
                max_len = max(max_len, r - l + 1);
            }
        }

        return max_len;
    }
};