package com.example.oderservice.application.cart;

import com.example.oderservice.domain.*;
import com.example.oderservice.infrastructure.http.cart.CartReadModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartService implements GetCart {
    @Value("${microservices.cart.url}")
    private String cartServiceUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Cart byUserId(UserId userId, String token) {
        final var headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList(String.format("Bearer %s", token)));
        final var entity = new HttpEntity<>(headers);

        final var response = restTemplate.exchange(
                String.format("%s/cart", cartServiceUrl),
                HttpMethod.GET,
                entity,
                CartReadModel.class);

        final var responseBody = Objects.requireNonNull(response.getBody());

        return Cart.builder()
                .id(CartId.builder()
                        .value(responseBody.id())
                        .build())
                .items(responseBody.items().stream()
                        .map(item -> CartItem.builder()
                                .product(Product.builder()
                                        .id(ProductId.builder()
                                                .value(item.productId())
                                                .build())
                                        .price(item.price())
                                        .brand(item.brand())
                                        .category(item.category())
                                        .thumbnail(item.thumbnail())
                                        .title(item.name())
                                        .build())
                                .quantity(item.quantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
