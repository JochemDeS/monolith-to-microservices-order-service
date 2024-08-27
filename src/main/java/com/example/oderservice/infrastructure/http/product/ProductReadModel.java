package com.example.oderservice.infrastructure.http.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;

@Schema(name = "ProductReadModel", description = "Model to read a product")
public record ProductReadModel(@Positive @Schema(description = "Product value", defaultValue = "4") long id,
                               @NotBlank @Schema(description = "Name of product", defaultValue = "Iphone X") String title,
                               @NotBlank @Schema(description = "Description of the product", defaultValue = "Made by Apple") String description,
                               @Positive @Schema(description = "Price of the product", defaultValue = "1250.99") double price,
                               @NotBlank @Schema(description = "Brand of the product", defaultValue = "Apple") String brand,
                               @NotBlank @Schema(description = "Category of the product", defaultValue = "Smartphone") String category,
                               @URL @Schema(description = "URL of thumbnail image", defaultValue = "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg") String thumbnail
) {
    private ProductReadModel(Builder builder) {
        this(builder.id, builder.title, builder.description, builder.price, builder.brand, builder.category, builder.thumbnail);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String title;
        private String description;
        private double price;
        private String brand;
        private String category;
        private String thumbnail;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public ProductReadModel build() {
            return new ProductReadModel(this);
        }
    }
}
