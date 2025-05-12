package com.twitterapp.controller;

import com.twitterapp.dto.AuthResponse;
import com.twitterapp.dto.LoginRequest;
import com.twitterapp.dto.RegisterRequest;
import com.twitterapp.entity.User;
import com.twitterapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request) {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build();

            User savedUser = userService.saveUser(user);

            return AuthResponse.builder()
                    .userId(savedUser.getId())
                    .username(savedUser.getUsername())
                    .email(savedUser.getEmail())
                    .message("Kayıt başarılı")
                    .build();
        } catch (IllegalArgumentException e) {
            return AuthResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request.getUsername(), request.getPassword())
                .map(user -> AuthResponse.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .message("Giriş başarılı")
                        .build())
                .orElse(AuthResponse.builder()
                        .message("Geçersiz kullanıcı adı veya şifre")
                        .build());
    }

}
