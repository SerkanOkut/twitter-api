package com.twitterapp.service;

import com.twitterapp.entity.Tweet;
import com.twitterapp.entity.User;
import com.twitterapp.repository.TweetRepository;
import com.twitterapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TweetServiceImplTest {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private TweetServiceImpl tweetService;

    @BeforeEach
    void setUp() {
        tweetRepository = mock(TweetRepository.class);
        userRepository = mock(UserRepository.class);
        tweetService = new TweetServiceImpl(tweetRepository, userRepository);
    }

    @Test
    void testCreateTweet_shouldReturnSavedTweet() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(tweetRepository.save(any(Tweet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Tweet result = tweetService.createTweet("Hello World", userId);

        assertNotNull(result);
        assertEquals("Hello World", result.getContent());
        assertEquals(user, result.getUser());
    }

    @Test
    void testUpdateTweet_shouldUpdateContent() {
        UUID userId = UUID.randomUUID();
        UUID tweetId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);

        Tweet existingTweet = new Tweet();
        existingTweet.setId(tweetId);
        existingTweet.setContent("Old Content");
        existingTweet.setUser(user);

        when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(existingTweet));
        when(tweetRepository.save(any(Tweet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Tweet updatedTweet = tweetService.updateTweet(tweetId, "New Content", userId);

        assertEquals("New Content", updatedTweet.getContent());
    }

    @Test
    void testUpdateTweet_shouldThrowIfNotOwner() {
        UUID ownerId = UUID.randomUUID();
        UUID otherUserId = UUID.randomUUID();
        UUID tweetId = UUID.randomUUID();

        User owner = new User();
        owner.setId(ownerId);

        Tweet tweet = new Tweet();
        tweet.setId(tweetId);
        tweet.setUser(owner);

        when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));

        assertThrows(RuntimeException.class, () -> {
            tweetService.updateTweet(tweetId, "New Content", otherUserId);
        });
    }

    @Test
    void testDeleteTweet_shouldDeleteIfOwner() {
        UUID tweetId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);

        Tweet tweet = new Tweet();
        tweet.setId(tweetId);
        tweet.setUser(user);

        when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));

        tweetService.deleteTweet(tweetId, userId);

        verify(tweetRepository, times(1)).delete(tweet);
    }

    @Test
    void testDeleteTweet_shouldNotDeleteIfNotOwner() {
        UUID tweetId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID otherId = UUID.randomUUID();

        User user = new User();
        user.setId(otherId);

        Tweet tweet = new Tweet();
        tweet.setId(tweetId);
        tweet.setUser(user);

        when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));

        tweetService.deleteTweet(tweetId, userId);

        verify(tweetRepository, never()).delete(tweet);
    }

    @Test
    void testGetTweetsByUserId_shouldReturnTweets() {
        UUID userId = UUID.randomUUID();
        Tweet tweet1 = new Tweet();
        Tweet tweet2 = new Tweet();
        List<Tweet> tweets = Arrays.asList(tweet1, tweet2);

        when(tweetRepository.findByUserId(userId)).thenReturn(tweets);

        List<Tweet> result = tweetService.getTweetsByUserId(userId);

        assertEquals(2, result.size());
    }
}
