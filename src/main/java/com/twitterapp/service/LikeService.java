package com.twitterapp.service;

import java.util.UUID;

public interface LikeService {
    void likeTweet(UUID userId, UUID tweetId);
}
