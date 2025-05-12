package com.twitterapp.controller;


import com.twitterapp.entity.CommentLike;
import com.twitterapp.service.CommentLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment-like")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    public CommentLikeController (CommentLikeService commentLikeService){
        this.commentLikeService = commentLikeService;
    }

    @PostMapping
    public CommentLike toggleCommentLike(@RequestParam UUID userId, @RequestParam UUID commentId) {
        return commentLikeService.toggleCommentLike(userId,commentId);

    }

    @GetMapping("/count/{commentId}")
    public long countCommentLikes(@PathVariable UUID commentId) {
        return commentLikeService.countLikesByCommentId(commentId);
    }

    @GetMapping("/by-comment/{commentId}")
    public List<CommentLike> getLikesByComment(@PathVariable UUID commentId) {
        return commentLikeService.getLikesByCommentId(commentId);
    }

    @GetMapping("/by-user/{userId}")
    public List<CommentLike> getLikesByUser(@PathVariable UUID userId) {
        return commentLikeService.getCommentLikesByUserId(userId);
    }

}
