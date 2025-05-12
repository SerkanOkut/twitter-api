package com.twitterapp.service;

import com.twitterapp.entity.Like;
import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.LikeRepository;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LikeServiceImplTest {

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private LikeServiceImpl likeService;

    @BeforeEach
    void setUp() {
        likeRepository = mock(LikeRepository.class);
        userRepository = mock(UserRepository.class);
        tweetRepository = mock(TweetRepository.class);
        likeService = new LikeServiceImpl(likeRepository, userRepository, tweetRepository);
    }

    @Test
    void testLikeTweet_shouldAddLikeWhenNotExists() {
        UUID userId = UUID.randomUUID();
        UUID tweetId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        Tweet tweet = new Tweet();
        tweet.setId(tweetId);

        when(likeRepository.findByUserIdAndTweetId(userId, tweetId)).thenReturn(Optional.empty());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
        when(likeRepository.save(any(Like.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Like savedLike = likeService.likeTweet(userId, tweetId);

        assertNotNull(savedLike);
        assertEquals(user, savedLike.getUser());
        assertEquals(tweet, savedLike.getTweet());
        verify(likeRepository).save(any(Like.class));
    }

    @Test
    void testLikeTweet_shouldRemoveLikeIfExists() {
        UUID userId = UUID.randomUUID();
        UUID tweetId = UUID.randomUUID();

        Like existingLike = new Like();
        existingLike.setId(UUID.randomUUID());

        when(likeRepository.findByUserIdAndTweetId(userId, tweetId)).thenReturn(Optional.of(existingLike));

        Like result = likeService.likeTweet(userId, tweetId);

        assertNull(result);
        verify(likeRepository).delete(existingLike);
    }
}
