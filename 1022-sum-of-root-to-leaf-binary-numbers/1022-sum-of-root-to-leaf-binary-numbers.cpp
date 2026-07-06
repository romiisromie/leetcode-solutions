class Solution {
private:
    int dfs(TreeNode* node, int current_val) {
        if (!node) return 0;
        
        current_val = (current_val << 1) | node->val;
        
        if (!node->left && !node->right) {
            return current_val;
        }
        
        return dfs(node->left, current_val) + dfs(node->right, current_val);
    }

public:
    int sumRootToLeaf(TreeNode* root) {
        return dfs(root, 0);
    }
};