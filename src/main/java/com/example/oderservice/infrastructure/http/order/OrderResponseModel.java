package com.example.oderservice.infrastructure.http.order;


import com.example.oderservice.infrastructure.http.user.DetailedUserModel;

import java.util.List;

public record OrderResponseModel(DetailedUserModel user, List<OrderReadModel> orders) {
    public OrderResponseModel(Builder builder) {
        this(builder.user, builder.orders);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private DetailedUserModel user;
        private List<OrderReadModel> orders;

        private Builder() {
        }

        public Builder user(DetailedUserModel user) {
            this.user = user;
            return this;
        }

        public Builder orders(List<OrderReadModel> orders) {
            this.orders = orders;
            return this;
        }

        public OrderResponseModel build() {
            return new OrderResponseModel(this);
        }
    }
}
