package com.example.oderservice.application.order;

import com.example.oderservice.application.common.UseCase;
import com.example.oderservice.application.exception.ResourceNotFoundException;
import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.OrderId;
import org.springframework.stereotype.Service;

@Service
public class GetOrderByIdUseCase implements UseCase<OrderId, Order> {
    private final GetOrderByIdPort getOrderByIdPort;

    public GetOrderByIdUseCase(GetOrderByIdPort getOrderByIdPort) {
        this.getOrderByIdPort = getOrderByIdPort;
    }

    @Override
    public Order handle(OrderId orderId) {
        return getOrderByIdPort.getOrderById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
