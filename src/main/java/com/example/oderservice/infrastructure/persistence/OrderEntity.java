package com.example.oderservice.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private long userId;
    @Column
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    public OrderEntity() {
    }

    private OrderEntity(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.createdAt = builder.createdAt;
        this.items = builder.items;
    }

    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private long userId;
        private LocalDateTime createdAt;
        private List<OrderItemEntity> items = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder items(List<OrderItemEntity> items) {
            this.items = items;
            return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
