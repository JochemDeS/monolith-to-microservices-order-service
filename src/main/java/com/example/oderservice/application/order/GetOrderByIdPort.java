package com.example.oderservice.application.order;


import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.OrderId;

import java.util.Optional;

public interface GetOrderByIdPort {
    Optional<Order> getOrderById(OrderId orderId);
}
