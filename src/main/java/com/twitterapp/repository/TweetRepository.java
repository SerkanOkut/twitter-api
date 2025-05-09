package com.twitterapp.repository;

import com.twitterapp.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TweetRepository  extends JpaRepository<Tweet, UUID> {
    List<Tweet> findByUserId(UUID userId);
}
