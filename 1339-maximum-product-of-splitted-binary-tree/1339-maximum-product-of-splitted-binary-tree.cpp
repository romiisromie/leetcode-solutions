#include <algorithm>
#include <vector>

class Solution {
private:
    long long totalSum = 0;
    long long maxProd = 0;

    int calculateTotalSum(TreeNode* root) {
        if (!root) return 0;
        return root->val + calculateTotalSum(root->left) + calculateTotalSum(root->right);
    }

    int checkMaxProduct(TreeNode* root) {
        if (!root) return 0;
        
        long long subtreeSum = root->val + checkMaxProduct(root->left) + checkMaxProduct(root->right);
        long long remainingSum = totalSum - subtreeSum;
        
        maxProd = std::max(maxProd, subtreeSum * remainingSum);
        
        return subtreeSum;
    }

public:
    int maxProduct(TreeNode* root) {
        totalSum = calculateTotalSum(root);
        maxProd = 0;
        checkMaxProduct(root);
        return maxProd % 1000000007;
    }
};