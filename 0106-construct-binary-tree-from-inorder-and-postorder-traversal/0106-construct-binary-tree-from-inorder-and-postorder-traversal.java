import java.util.HashMap;
import java.util.Map;

class Solution {
    private int postIdx;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        map = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        return helper(postorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }

        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);

        int inorderIdx = map.get(rootVal);

        root.right = helper(postorder, inorderIdx + 1, right);
        root.left = helper(postorder, left, inorderIdx - 1);

        return root;
    }
}