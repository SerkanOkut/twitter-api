package com.twitterapp.service;

import com.twitterapp.entity.Retweet;

import java.util.List;
import java.util.UUID;

public interface RetweetService {
    Retweet toggleRetweet(UUID tweetId, UUID userId);
    List<Retweet> getRetweetsByUserId(UUID userId);
    List<Retweet> getRetweetsByTweetId(UUID tweetId);

}
