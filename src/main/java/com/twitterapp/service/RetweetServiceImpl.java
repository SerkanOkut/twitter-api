package com.twitterapp.service;

import com.twitterapp.entity.Retweet;
import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.RetweetRepository;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public RetweetServiceImpl(RetweetRepository retweetRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.retweetRepository = retweetRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void toggleRetweet(UUID userId, UUID tweetId) {
        Retweet existing = retweetRepository.findByUserIdAndTweetId(userId, tweetId).orElse(null);

        if (existing != null) {
            retweetRepository.delete(existing);
        } else {
            User user = userRepository.findById(userId).orElse(null);
            Tweet tweet = tweetRepository.findById(tweetId).orElse(null);
            if (user == null || tweet == null) return;

            Retweet retweet = Retweet.builder()
                    .user(user)
                    .tweet(tweet)
                    .createdAt(LocalDateTime.now())
                    .build();

            retweetRepository.save(retweet);
        }
    }
}
