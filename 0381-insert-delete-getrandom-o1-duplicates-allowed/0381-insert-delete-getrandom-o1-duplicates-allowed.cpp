#include <unordered_map>
#include <unordered_set>
#include <vector>
#include <cstdlib>

class RandomizedCollection {
private:
    std::vector<int> nums;
    std::unordered_map<int, std::unordered_set<int>> valToIndices;

public:
    RandomizedCollection() {
        
    }
    
    bool insert(int val) {
        bool notPresent = valToIndices[val].empty();
        valToIndices[val].insert(nums.size());
        nums.push_back(val);
        return notPresent;
    }
    
    bool remove(int val) {
        if (valToIndices[val].empty()) {
            return false;
        }
        
        int indexToRemove = *valToIndices[val].begin();
        valToIndices[val].erase(indexToRemove);
        
        int lastElement = nums.back();
        int lastIndex = nums.size() - 1;
        
        if (indexToRemove != lastIndex) {
            nums[indexToRemove] = lastElement;
            valToIndices[lastElement].erase(lastIndex);
            valToIndices[lastElement].insert(indexToRemove);
        }
        
        nums.pop_back();
        return true;
    }
    
    int getRandom() {
        return nums[std::rand() % nums.size()];
    }
};