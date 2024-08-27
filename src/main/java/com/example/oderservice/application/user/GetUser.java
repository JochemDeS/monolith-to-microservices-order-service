package com.example.oderservice.application.user;

import com.example.oderservice.domain.User;

import java.util.Optional;

public interface GetUser {
    Optional<User> byToken(String token);
}
