package com.twitterapp.service;

import java.util.UUID;

public interface RetweetService {
    void toggleRetweet(UUID userId, UUID tweetId);
}
