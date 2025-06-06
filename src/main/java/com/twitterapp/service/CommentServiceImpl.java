package com.twitterapp.service;

import com.twitterapp.entity.Comment;
import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.CommentRepository;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TweetRepository tweetRepository , UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Comment createComment(String content, UUID tweetId, UUID userId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (tweet == null || user == null) {
            return null;
        }
        Comment comment = Comment.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .tweet(tweet)
                .user(user)
                .build();
        return  commentRepository.save(comment);
    }
    @Override
    public Comment updateComment(UUID commentId, String content, UUID currentUserId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (comment == null) return null;

        if (!comment.getUser().getId().equals(currentUserId)) return null;

        comment.setContent(content);
        return commentRepository.save(comment);
    }
    @Override
    public void deleteComment(UUID commentId, UUID currentUserId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (comment != null) {
            UUID commentOwnerId = comment.getUser().getId();
            UUID tweetOwnerId = comment.getTweet().getUser().getId();

            if (commentOwnerId.equals(currentUserId) || tweetOwnerId.equals(currentUserId)) {
                commentRepository.delete(comment);
            }
        }
    }


}
