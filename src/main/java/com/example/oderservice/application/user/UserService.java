package com.example.oderservice.application.user;

import com.example.oderservice.domain.Name;
import com.example.oderservice.domain.User;
import com.example.oderservice.domain.UserId;
import com.example.oderservice.infrastructure.http.user.DetailedUserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements GetUser {
    @Value("${microservices.user.url}")
    private String userServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<User> byToken(String token) {
        final var headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList(String.format("Bearer %s", token)));
        final var entity = new HttpEntity<>(headers);

        final var response = restTemplate.exchange(
                String.format("%s/auth/me", userServiceUrl),
                HttpMethod.GET,
                entity,
                DetailedUserModel.class);

        return Optional.ofNullable(response.getBody())
                .map(user -> User.builder()
                        .id(UserId.builder()
                                .value((long) user.id())
                                .build())
                        .username(user.username())
                        .token(token)
                        .email(user.email())
                        .name(Name.builder()
                                .firstName(user.firstName())
                                .lastName(user.lastName())
                                .build())
                        .build());
    }
}
