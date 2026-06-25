#include <stdlib.h>

typedef struct Node {
    int key;
    int value;
    struct Node* prev;
    struct Node* next;
} Node;

typedef struct {
    int capacity;
    int size;
    Node* head;
    Node* tail;
    Node** hash;
} LRUCache;

Node* createNode(int key, int value) {
    Node* node = (Node*)malloc(sizeof(Node));
    node->key = key;
    node->value = value;
    node->prev = NULL;
    node->next = NULL;
    return node;
}

LRUCache* lRUCacheCreate(int capacity) {
    LRUCache* cache = (LRUCache*)malloc(sizeof(LRUCache));
    cache->capacity = capacity;
    cache->size = 0;
    
    cache->head = createNode(-1, -1);
    cache->tail = createNode(-1, -1);
    cache->head->next = cache->tail;
    cache->tail->prev = cache->head;
    
    cache->hash = (Node**)calloc(10001, sizeof(Node*));
    return cache;
}

void removeNode(Node* node) {
    node->prev->next = node->next;
    node->next->prev = node->prev;
}

void addToHead(LRUCache* cache, Node* node) {
    node->next = cache->head->next;
    node->prev = cache->head;
    cache->head->next->prev = node;
    cache->head->next = node;
}

void moveToHead(LRUCache* cache, Node* node) {
    removeNode(node);
    addToHead(cache, node);
}

int lRUCacheGet(LRUCache* obj, int key) {
    if (key < 0 || key > 10000 || !obj->hash[key]) {
        return -1;
    }
    Node* node = obj->hash[key];
    moveToHead(obj, node);
    return node->value;
}

void lRUCachePut(LRUCache* obj, int key, int value) {
    if (obj->hash[key]) {
        Node* node = obj->hash[key];
        node->value = value;
        moveToHead(obj, node);
    } else {
        Node* newNode = createNode(key, value);
        obj->hash[key] = newNode;
        addToHead(obj, newNode);
        obj->size++;
        
        if (obj->size > obj->capacity) {
            Node* lru = obj->tail->prev;
            obj->hash[lru->key] = NULL;
            removeNode(lru);
            free(lru);
            obj->size--;
        }
    }
}

void lRUCacheFree(LRUCache* obj) {
    Node* curr = obj->head;
    while (curr) {
        Node* next = curr->next;
        free(curr);
        curr = next;
    }
    free(obj->hash);
    free(obj);
}