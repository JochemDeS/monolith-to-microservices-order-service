package com.example.oderservice.domain;

public record OrderItem(OrderId orderId, Product product, int quantity) {
    public OrderItem(Builder builder) {
        this(builder.orderId, builder.product, builder.quantity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderId orderId;
        private Product product;
        private int quantity;

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
