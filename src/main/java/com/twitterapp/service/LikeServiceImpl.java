package com.twitterapp.service;

import com.twitterapp.entity.Like;
import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.LikeRepository;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, TweetRepository tweetRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }
    @Override
    public void likeTweet(UUID userId, UUID tweetId) {
        var existingLike = likeRepository.findByUserIdAndTweetId(userId, tweetId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get()); // varsa kaldır
        } else {
            User user = userRepository.findById(userId).orElse(null);
            Tweet tweet = tweetRepository.findById(tweetId).orElse(null);
            if (user != null && tweet != null) {
                Like like = Like.builder()
                        .user(user)
                        .tweet(tweet)
                        .build();
                likeRepository.save(like); // yoksa ekle
            }
        }
    }
}