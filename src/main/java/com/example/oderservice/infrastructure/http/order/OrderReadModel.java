package com.example.oderservice.infrastructure.http.order;

import java.util.List;

public record OrderReadModel(long id, double totalAmount, List<OrderItemReadModel> items) {
    public OrderReadModel(Builder builder) {
        this(builder.id, builder.totalAmount, builder.items);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private double totalAmount;
        private List<OrderItemReadModel> items;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder totalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder items(List<OrderItemReadModel> items) {
            this.items = items;
            return this;
        }

        public OrderReadModel build() {
            return new OrderReadModel(id, totalAmount, items);
        }
    }
}
