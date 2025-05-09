package com.twitterapp.service;

import com.twitterapp.entity.Tweet;

import java.util.List;
import java.util.UUID;

public interface TweetService {
    Tweet createTweet(String content , UUID userId);
    List<Tweet> getTweetsByUserId(UUID userId);
    Tweet getTweetById (UUID tweetId);
    Tweet updateTweet (UUID tweetId , String content);
    void deleteTweet(UUID tweetId , UUID currentUserId);
}
