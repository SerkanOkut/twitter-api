package com.twitterapp.service;

import com.twitterapp.entity.CommentLike;

import java.util.List;
import java.util.UUID;

public interface CommentLikeService {
    CommentLike toggleCommentLike(UUID userId, UUID commentId);
    long countLikesByCommentId(UUID commentId);
    List<CommentLike> getLikesByCommentId(UUID commentId);
    List<CommentLike> getCommentLikesByUserId(UUID userId);
}
