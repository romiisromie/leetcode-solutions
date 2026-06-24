#include <unordered_map>
#include <vector>
#include <cstdlib>

class RandomizedSet {
private:
    std::vector<int> nums;
    std::unordered_map<int, int> valToIndex;

public:
    RandomizedSet() {
        
    }
    
    bool insert(int val) {
        if (valToIndex.find(val) != valToIndex.end()) {
            return false;
        }
        valToIndex[val] = nums.size();
        nums.push_back(val);
        return true;
    }
    
    bool remove(int val) {
        if (valToIndex.find(val) == valToIndex.end()) {
            return false;
        }
        int indexToRemove = valToIndex[val];
        int lastElement = nums.back();
        
        nums[indexToRemove] = lastElement;
        valToIndex[lastElement] = indexToRemove;
        
        nums.pop_back();
        valToIndex.erase(val);
        return true;
    }
    
    int getRandom() {
        int randomIndex = std::rand() % nums.size();
        return nums[randomIndex];
    }
};