package com.twitterapp.repository;

import com.twitterapp.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RetweetRepository extends JpaRepository<Retweet, UUID> {
    Optional<Retweet> findByUserIdAndTweetId(UUID userId, UUID tweetId);
}
