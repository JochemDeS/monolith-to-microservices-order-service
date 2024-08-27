package com.example.oderservice.infrastructure.http.order;


import com.example.oderservice.infrastructure.http.product.ProductReadModel;

public record OrderItemReadModel(ProductReadModel product, int quantity) {
    public OrderItemReadModel(Builder builder) {
        this(builder.product, builder.quantity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ProductReadModel product;
        private int quantity;

        private Builder() {
        }

        public Builder product(ProductReadModel product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemReadModel build() {
            return new OrderItemReadModel(this);
        }
    }
}
