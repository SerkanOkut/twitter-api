package com.twitterapp.service;

import com.twitterapp.entity.Comment;

import java.util.UUID;

public interface CommentService {
    Comment createComment(String content, UUID tweetId , UUID userId);
    Comment updateComment(UUID commentId, String content, UUID currentUserId);
    void deleteComment(UUID commentId, UUID currentUserId);

}
