package com.twitterapp.service;


import com.twitterapp.entity.Comment;
import com.twitterapp.entity.CommentLike;
import com.twitterapp.entity.User;
import com.twitterapp.repository.CommentLikeRepository;
import com.twitterapp.repository.CommentRepository;
import com.twitterapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentLikeServiceImpl implements CommentLikeService{
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.commentLikeRepository = commentLikeRepository;
        this.userRepository= userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentLike toggleCommentLike(UUID userId, UUID commentId) {
        var existingLike = commentLikeRepository.findByUserIdAndCommentId(userId,commentId);
        if (existingLike.isPresent()){
            commentLikeRepository.delete(existingLike.get());
            return null;
        }
        User user = userRepository.findById(userId).orElse(null);
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (user == null || comment == null) return null;

        CommentLike like = CommentLike.builder()
                .user(user)
                .comment(comment)
                .build();
        return commentLikeRepository.save(like);
    }

    @Override
    public long countLikesByCommentId (UUID commentId) {
        return  commentLikeRepository.countByCommentId(commentId);
    }

    @Override
    public List<CommentLike> getLikesByCommentId(UUID commentId){
        return commentLikeRepository.findByCommentId(commentId);
    }

    @Override
    public List<CommentLike> getCommentLikesByUserId (UUID userId) {
        return commentLikeRepository.findByUserId(userId);
    }
}
