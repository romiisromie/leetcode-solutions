public class Solution {
    private int maxLength = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxLength;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftPath = dfs(node.left);
        int rightPath = dfs(node.right);

        int leftCheck = 0;
        int rightCheck = 0;

        if (node.left != null && node.left.val == node.val) {
            leftCheck = leftPath + 1;
        }

        if (node.right != null && node.right.val == node.val) {
            rightCheck = rightPath + 1;
        }

        maxLength = Math.max(maxLength, leftCheck + rightCheck);

        return Math.max(leftCheck, rightCheck);
    }
}