public class NumArray {
    private final int[] tree;
    private final int[] nums;
    private final int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            init(i, nums[i]);
        }
    }

    private void init(int i, int val) {
        int idx = i + 1;
        while (idx <= n) {
            tree[idx] += val;
            idx += idx & (-idx);
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        int idx = index + 1;
        while (idx <= n) {
            tree[idx] += delta;
            idx += idx & (-idx);
        }
    }

    private int query(int i) {
        int sum = 0;
        int idx = i + 1;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    public int sumRange(int left, int right) {
        return query(right) - query(left - 1);
    }
}