package com.twitterapp.service;

import com.twitterapp.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    Optional<User> login(String username, String rawPassword);

}
