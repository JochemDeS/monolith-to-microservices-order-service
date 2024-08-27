package com.example.oderservice.application.order;

import com.example.oderservice.domain.UserId;

public record CreateOrder(UserId userId, String token) {
    public CreateOrder(Builder builder) {
        this(builder.userId, builder.token);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserId userId;
        private String token;

        private Builder() {
        }

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public CreateOrder build() {
            return new CreateOrder(this);
        }
    }
}
