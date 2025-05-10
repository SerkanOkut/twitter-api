package com.twitterapp.repository;

import com.twitterapp.entity.Like;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByUserIdAndTweetId(UUID userId, UUID tweetId);
    long countByTweetId(UUID tweetId);
    List<Like> findByTweetId(UUID tweetId);
    List<Like> findByUserId(UUID userId);
}
