package com.twitterapp.service;

import com.twitterapp.entity.Retweet;
import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.RetweetRepository;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public RetweetServiceImpl(RetweetRepository retweetRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.retweetRepository = retweetRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Retweet toggleRetweet(UUID tweetId, UUID userId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (tweet == null || user == null) return null;

        Retweet existingRetweet = retweetRepository.findByUserIdAndTweetId(userId, tweetId);
        if (existingRetweet != null) {
            retweetRepository.delete(existingRetweet);
            return null;
        }

        Retweet newRetweet = Retweet.builder()
                .user(user)
                .tweet(tweet)
                .createdAt(LocalDateTime.now())
                .build();

        return retweetRepository.save(newRetweet);
    }

    @Override
    public List<Retweet> getRetweetsByUserId(UUID userId) {
        return retweetRepository.findByUserId(userId);
    }

    @Override
    public List<Retweet> getRetweetsByTweetId(UUID tweetId) {
        return retweetRepository.findByTweetId(tweetId);
    }

    @Override
    public long countRetweetsByTweetId(UUID tweetId) {
        return retweetRepository.countByTweetId(tweetId);
    }

}
