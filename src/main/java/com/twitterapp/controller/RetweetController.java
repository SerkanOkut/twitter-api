package com.twitterapp.controller;

import com.twitterapp.entity.Retweet;
import com.twitterapp.service.RetweetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;

    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public Retweet toggleRetweet(@RequestParam UUID tweetId, @RequestParam UUID userId) {
        return retweetService.toggleRetweet(tweetId, userId);
    }

    @GetMapping("/user/{userId}")
    public List<Retweet> getRetweetsByUser(@PathVariable UUID userId) {
        return retweetService.getRetweetsByUserId(userId);
    }

    @GetMapping("/tweet/{tweetId}")
    public List<Retweet> getRetweetsByTweet(@PathVariable UUID tweetId) {
        return retweetService.getRetweetsByTweetId(tweetId);
    }

}
