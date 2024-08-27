package com.example.oderservice.domain;

public record OrderId(long value) {
    private OrderId(Builder builder) {
        this(builder.value);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long value;

        public Builder value(long id) {
            this.value = id;
            return this;
        }

        public Builder value(String id) {
            this.value = Long.parseLong(id);
            return this;
        }

        public OrderId build() {
            return new OrderId(this);
        }
    }
}
