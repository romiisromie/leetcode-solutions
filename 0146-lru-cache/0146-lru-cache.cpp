#include <unordered_map>
#include <list>

class LRUCache {
private:
    int cap;
    std::list<std::pair<int, int>> cacheList;
    std::unordered_map<int, std::list<std::pair<int, int>>::iterator> cacheMap;

public:
    LRUCache(int capacity) {
        cap = capacity;
    }
    
    int get(int key) {
        if (cacheMap.find(key) == cacheMap.end()) {
            return -1;
        }
        auto it = cacheMap[key];
        int value = it->second;
        cacheList.erase(it);
        cacheList.push_front({key, value});
        cacheMap[key] = cacheList.begin();
        return value;
    }
    
    void put(int key, int value) {
        if (cacheMap.find(key) != cacheMap.end()) {
            auto it = cacheMap[key];
            cacheList.erase(it);
        } else if (cacheList.size() == cap) {
            auto last = cacheList.back();
            cacheMap.erase(last.first);
            cacheList.pop_back();
        }
        cacheList.push_front({key, value});
        cacheMap[key] = cacheList.begin();
    }
};