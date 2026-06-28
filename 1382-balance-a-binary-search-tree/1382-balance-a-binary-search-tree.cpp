#include <vector>

class Solution {
private:
    std::vector<TreeNode*> nodes;

    void inorder(TreeNode* root) {
        if (!root) return;
        inorder(root->left);
        nodes.push_back(root);
        inorder(root->right);
    }

    TreeNode* buildTree(int start, int end) {
        if (start > end) return nullptr;
        
        int mid = start + (end - start) / 2;
        TreeNode* root = nodes[mid];
        
        root->left = buildTree(start, mid - 1);
        root->right = buildTree(mid + 1, end);
        
        return root;
    }

public:
    TreeNode* balanceBST(TreeNode* root) {
        nodes.clear();
        inorder(root);
        return buildTree(0, nodes.size() - 1);
    }
};