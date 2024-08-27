package com.example.oderservice.infrastructure.persistence;


import com.example.oderservice.domain.*;

import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toDomain(OrderEntity orderEntity) {
        final var items = orderEntity.getItems().stream()
                .map(orderItemEntity -> OrderItem.builder()
                        .orderId(OrderId.builder()
                            .value(orderItemEntity.getOrder().getId())
                            .build())
                        .product(Product.builder()
                                .id(ProductId.builder()
                                        .value(orderItemEntity.getProduct())
                                        .build())
                                .build())
                        .quantity(orderItemEntity.getQuantity())
                        .build())
                .collect(Collectors.toList());


        return Order.builder()
                .id(orderEntity.getId())
                .items(items)
                .build();
    }

    public static OrderEntity toEntity(Order order, UserId userId) {
        final var orderEntity = OrderEntity.builder()
                .id(order.id() != null ? order.id().value() : null)
                .userId(userId.value())
                .build();

        final var items = order.items().stream()
                .map(orderItem -> OrderItemEntity.builder()
                        .order(orderEntity)
                        .productId(orderItem.product()
                                .id()
                                .value())
                        .quantity(orderItem.quantity())
                        .build())
                .collect(Collectors.toList());

        orderEntity.setItems(items);

        return orderEntity;
    }
}
