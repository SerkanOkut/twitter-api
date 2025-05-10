package com.twitterapp.controller;


import com.twitterapp.service.RetweetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/retweet")
public class RetweetController {
    private final RetweetService retweetService;

    public RetweetController(RetweetService retweetService){
        this.retweetService = retweetService;
    }

    @PostMapping
    public void toggleRetweet (@RequestParam UUID userId, @RequestParam UUID tweetId) {
        retweetService.toggleRetweet(userId,tweetId);
    }
}
