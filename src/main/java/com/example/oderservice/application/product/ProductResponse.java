package com.example.oderservice.application.product;

public record ProductResponse(
        long id,
        String title,
        String description,
        double price,
        int stock,
        String brand,
        String category,
        String thumbnail,
        String image
) {
}
