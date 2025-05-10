package com.twitterapp.repository;

import com.twitterapp.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RetweetRepository extends JpaRepository<Retweet, UUID> {
    Retweet findByUserIdAndTweetId(UUID userId, UUID tweetId);
    List<Retweet> findByUserId(UUID userId);
    List<Retweet> findByTweetId(UUID tweetId);
    long countByTweetId(UUID tweetId);

}
