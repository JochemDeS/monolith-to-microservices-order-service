package com.example.oderservice.application.order;

import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.UserId;

import java.util.List;


public interface GetOrdersByUserIdPort {
    List<Order> byUserId(UserId userId);
}
