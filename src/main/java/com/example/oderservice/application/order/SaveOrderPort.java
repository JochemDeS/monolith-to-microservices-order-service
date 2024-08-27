package com.example.oderservice.application.order;

import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.UserId;

public interface SaveOrderPort {
    Order save(Order order, UserId userId);
}
