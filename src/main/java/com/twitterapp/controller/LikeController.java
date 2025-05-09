package com.twitterapp.controller;

import com.twitterapp.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public void likeTweet(@RequestParam UUID userId,
                          @RequestParam UUID tweetId) {
        likeService.likeTweet(userId,tweetId);
    }

}
