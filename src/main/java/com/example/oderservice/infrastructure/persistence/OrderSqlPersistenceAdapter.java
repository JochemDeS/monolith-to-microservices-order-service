package com.example.oderservice.infrastructure.persistence;


import com.example.oderservice.application.order.GetOrderByIdPort;
import com.example.oderservice.application.order.GetOrdersByUserIdPort;
import com.example.oderservice.application.order.SaveOrderPort;
import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.OrderId;
import com.example.oderservice.domain.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class OrderSqlPersistenceAdapter implements GetOrdersByUserIdPort, GetOrderByIdPort, SaveOrderPort {
    private final OrderRepository orderRepository;

    public OrderSqlPersistenceAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> byUserId(UserId userId) {
        final var orders = orderRepository.findAllByUserId(userId.value());
        return orders.stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Order> getOrderById(OrderId orderId) {
        return orderRepository.findById(orderId.value())
                .map(OrderMapper::toDomain);
    }

    @Override
    public Order save(Order order, UserId userId) {
        final var orderEntity = OrderMapper.toEntity(order, userId);
        final var savedOrder = orderRepository.save(orderEntity);
        return OrderMapper.toDomain(savedOrder);
    }
}
