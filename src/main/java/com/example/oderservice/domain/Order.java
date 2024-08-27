package com.example.oderservice.domain;

import java.util.List;

public record Order(OrderId id, List<OrderItem> items) {
    private Order(Builder builder) {
        this(builder.id, builder.items);
    }

    public double getTotalAmount() {
        return items.stream()
                .mapToDouble(item -> item.product().price() * item.quantity())
                .sum();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderId id;
        private List<OrderItem> items;

        public Builder id(long id) {
            this.id = OrderId.builder().value(id).build();
            return this;
        }

        public Builder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
