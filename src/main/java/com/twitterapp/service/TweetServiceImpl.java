package com.twitterapp.service;

import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Tweet createTweet(String content, UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        Tweet tweet = new Tweet();
        tweet.setContent(content);
        tweet.setUser(user);
        tweet.setCreatedAt(LocalDateTime.now());

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getTweetsByUserId(UUID userId) {
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public Tweet getTweetById(UUID tweetId) {
        return tweetRepository.findById(tweetId).orElse(null);
    }

    @Override
    public Tweet updateTweet(UUID tweetId, String content, UUID userId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));

        if (!tweet.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bu tweet'i yalnızca sahibi güncelleyebilir.");
        }

        tweet.setContent(content);
        return tweetRepository.save(tweet);
    }


    @Override
    public void deleteTweet(UUID tweetId, UUID currentUserId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElse(null);
        if (tweet != null && tweet.getUser().getId().equals(currentUserId)) {
            tweetRepository.delete(tweet);
        }
    }
}
