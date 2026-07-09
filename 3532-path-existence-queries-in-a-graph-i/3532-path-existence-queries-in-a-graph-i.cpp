class Solution {
public:
    vector<bool> pathExistenceQueries(int n, vector<int>& nums, int maxDiff, vector<vector<int>>& queries) {
        vector<int> component(n, 0);
        int curr_id = 0;
        
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                curr_id = i;
            }
            component[i] = curr_id;
        }
        
        vector<bool> ans;
        ans.reserve(queries.size());
        
        for (const auto& q : queries) {
            ans.push_back(component[q[0]] == component[q[1]]);
        }
        
        return ans;
    }
};