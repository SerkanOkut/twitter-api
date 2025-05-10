package com.twitterapp.service;

import com.twitterapp.entity.Like;

import java.util.List;
import java.util.UUID;

public interface LikeService {
    Like likeTweet(UUID userId, UUID tweetId);
    long countLikesByTweetId(UUID tweetId);
    List<Like> getLikesByTweetId(UUID tweetId);
    List<Like> getLikesByUserId(UUID userId);
}
