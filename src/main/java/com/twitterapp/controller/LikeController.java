package com.twitterapp.controller;

import com.twitterapp.entity.Like;
import com.twitterapp.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public Like likeTweet(@RequestParam UUID userId, @RequestParam UUID tweetId) {
        return likeService.likeTweet(userId, tweetId);
    }

    @GetMapping("/count/{tweetId}")
    public long countLikes(@PathVariable UUID tweetId) {
        return likeService.countLikesByTweetId(tweetId);
    }

    @GetMapping("/tweet/{tweetId}")
    public List<Like> getLikesByTweetId(@PathVariable UUID tweetId) {
        return likeService.getLikesByTweetId(tweetId);
    }

    @GetMapping("/user/{userId}")
    public List<Like> getLikesByUserId(@PathVariable UUID userId) {
        return likeService.getLikesByUserId(userId);
    }
}
