package com.example.oderservice.domain;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Builder builder) {
        this.product = builder.product;
        this.quantity = builder.quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Product product;
        private int quantity;

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}
