package com.twitterapp.repository;

import com.twitterapp.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentLikeRepository extends JpaRepository<CommentLike, UUID> {
    Optional<CommentLike> findByUserIdAndCommentId(UUID userId, UUID commentId);
    long countByCommentId (UUID commentId);
    List<CommentLike> findByCommentId(UUID commentId);
    List<CommentLike> findByUserId(UUID userId);
}
