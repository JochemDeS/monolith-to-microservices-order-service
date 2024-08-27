package com.example.oderservice.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final CartId id;
    private final List<CartItem> items;

    private Cart(Builder builder) {
        this.id = builder.id;
        this.items = builder.items;
    }

    public CartId getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getProduct().price() * item.getQuantity()).sum();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CartId id;
        private List<CartItem> items = new ArrayList<>();

        public Builder id(CartId id) {
            this.id = id;
            return this;
        }

        public Builder items(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
