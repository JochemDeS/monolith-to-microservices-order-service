package com.example.oderservice.infrastructure.persistence;

import jakarta.persistence.*;

@Entity(name = "OrderItem")
@Table(name = "order_items")
public class OrderItemEntity {
    @Id @ManyToOne @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @Column
    private long productId;
    @Column
    private int quantity;

    public OrderItemEntity() {
    }

    private OrderItemEntity(Builder builder) {
        this.order = builder.order;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public long getProduct() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderEntity order;
        private long productId;
        private int quantity;

        public Builder order(OrderEntity order) {
            this.order = order;
            return this;
        }

        public Builder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemEntity build() {
            return new OrderItemEntity(this);
        }
    }
}
