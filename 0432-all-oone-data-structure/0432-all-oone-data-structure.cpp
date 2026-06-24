#include <string>
#include <unordered_map>
#include <unordered_set>
#include <list>

class AllOne {
private:
    struct Node {
        int count;
        std::unordered_set<std::string> keys;
    };

    std::list<Node> bucketList;
    std::unordered_map<std::string, std::list<Node>::iterator> keyToBucket;

public:
    AllOne() {
        
    }
    
    void inc(std::string key) {
        if (keyToBucket.find(key) == keyToBucket.end()) {
            if (bucketList.empty() || bucketList.front().count > 1) {
                bucketList.push_front({1, {key}});
            } else {
                bucketList.front().keys.insert(key);
            }
            keyToBucket[key] = bucketList.begin();
        } else {
            auto curBucket = keyToBucket[key];
            auto nextBucket = std::next(curBucket);
            int nextCount = curBucket->count + 1;
            
            if (nextBucket == bucketList.end() || nextBucket->count > nextCount) {
                nextBucket = bucketList.insert(nextBucket, {nextCount, {key}});
            } else {
                nextBucket->keys.insert(key);
            }
            
            keyToBucket[key] = nextBucket;
            curBucket->keys.erase(key);
            if (curBucket->keys.empty()) {
                bucketList.erase(curBucket);
            }
        }
    }
    
    void dec(std::string key) {
        auto curBucket = keyToBucket[key];
        int prevCount = curBucket->count - 1;
        
        if (prevCount == 0) {
            keyToBucket.erase(key);
        } else {
            auto prevBucket = std::prev(curBucket);
            if (curBucket == bucketList.begin() || prevBucket->count < prevCount) {
                prevBucket = bucketList.insert(curBucket, {prevCount, {key}});
            } else {
                prevBucket->keys.insert(key);
            }
            keyToBucket[key] = prevBucket;
        }
        
        curBucket->keys.erase(key);
        if (curBucket->keys.empty()) {
            bucketList.erase(curBucket);
        }
    }
    
    std::string getMaxKey() {
        if (bucketList.empty()) {
            return "";
        }
        return *(bucketList.back().keys.begin());
    }
    
    std::string getMinKey() {
        if (bucketList.empty()) {
            return "";
        }
        return *(bucketList.front().keys.begin());
    }
};