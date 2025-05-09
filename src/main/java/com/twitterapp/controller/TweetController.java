package com.twitterapp.controller;

import com.twitterapp.entity.Tweet;
import com.twitterapp.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tweet")
@AllArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    @PostMapping
    public Tweet createTweet(@RequestParam String content, @RequestParam UUID userId) {
        return  tweetService.createTweet(content, userId);
    }

    @GetMapping("/findByUserId")
    public List<Tweet> getTweetsByUserId(@RequestParam UUID userId) {
        return tweetService.getTweetsByUserId(userId);
    }
    @GetMapping("/findById")
    public Tweet getTweetById(@RequestParam UUID tweetId) {
        return tweetService.getTweetById(tweetId);
    }

    @PutMapping("/{id}")
    public Tweet updateTweet(@PathVariable UUID id, @RequestParam String content) {
        return tweetService.updateTweet(id,content);
    }

    @DeleteMapping("/{id}")
    public void deleteTweet(@PathVariable UUID id, @RequestParam UUID userId){
        tweetService.deleteTweet(id,userId);
    }
}
