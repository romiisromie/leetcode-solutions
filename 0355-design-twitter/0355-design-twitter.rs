use std::collections::{HashMap, HashSet, BinaryHeap};

struct Tweet {
    id: i32,
    time: usize,
}

struct Twitter {
    timestamp: usize,
    tweets: HashMap<i32, Vec<Tweet>>,
    following: HashMap<i32, HashSet<i32>>,
}

impl Twitter {
    fn new() -> Self {
        Twitter {
            timestamp: 0,
            tweets: HashMap::new(),
            following: HashMap::new(),
        }
    }
    
    fn post_tweet(&mut self, user_id: i32, tweet_id: i32) {
        self.timestamp += 1;
        self.tweets
            .entry(user_id)
            .or_insert_with(Vec::new)
            .push(Tweet {
                id: tweet_id,
                time: self.timestamp,
            });
    }
    
    fn get_news_feed(&self, user_id: i32) -> Vec<i32> {
        let mut heap = BinaryHeap::new();
        
        if let Some(user_tweets) = self.tweets.get(&user_id) {
            if let Some(last_tweet) = user_tweets.last() {
                let idx = user_tweets.len() - 1;
                heap.push((last_tweet.time, last_tweet.id, user_id, idx));
            }
        }
        
        if let Some(followed_users) = self.following.get(&user_id) {
            for &followee_id in followed_users {
                if followee_id == user_id {
                    continue;
                }
                if let Some(followee_tweets) = self.tweets.get(&followee_id) {
                    if let Some(last_tweet) = followee_tweets.last() {
                        let idx = followee_tweets.len() - 1;
                        heap.push((last_tweet.time, last_tweet.id, followee_id, idx));
                    }
                }
            }
        }
        
        let mut res = Vec::new();
        while let Some((_time, tweet_id, author_id, idx)) = heap.pop() {
            res.push(tweet_id);
            if res.len() == 10 {
                break;
            }
            if idx > 0 {
                let next_idx = idx - 1;
                let next_tweet = &self.tweets.get(&author_id).unwrap()[next_idx];
                heap.push((next_tweet.time, next_tweet.id, author_id, next_idx));
            }
        }
        
        res
    }
    
    fn follow(&mut self, follower_id: i32, followee_id: i32) {
        self.following
            .entry(follower_id)
            .or_insert_with(HashSet::new)
            .insert(followee_id);
    }
    
    fn unfollow(&mut self, follower_id: i32, followee_id: i32) {
        if let Some(followed_users) = self.following.get_mut(&follower_id) {
            followed_users.remove(&followee_id);
        }
    }
}