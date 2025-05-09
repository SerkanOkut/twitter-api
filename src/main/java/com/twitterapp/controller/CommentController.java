package com.twitterapp.controller;

import com.twitterapp.entity.Comment;
import com.twitterapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private  final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public Comment createComment(@RequestParam String content,
                                 @RequestParam UUID tweetId,
                                 @RequestParam UUID userId) {
        return commentService.createComment(content, tweetId, userId);
    }
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable UUID id,
                                 @RequestParam String content,
                                 @RequestParam UUID userId) {
        return commentService.updateComment(id, content, userId);
    }

}
