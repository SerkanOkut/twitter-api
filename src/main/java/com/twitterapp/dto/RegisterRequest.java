package com.twitterapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Kullanıcı adı boş olamaz")
    private String username;

    @Email(message = "Geçerli bir e-posta adresi girin")
    private String email;

    @Size(min=6, message = "Şifre en az 6 karakter olmalı")
    private String password;
}
