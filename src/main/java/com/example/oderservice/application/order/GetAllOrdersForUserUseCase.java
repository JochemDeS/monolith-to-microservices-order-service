package com.example.oderservice.application.order;

import com.example.oderservice.application.common.UseCase;
import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.UserId;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetAllOrdersForUserUseCase implements UseCase<UserId, List<Order>> {
    private final GetOrdersByUserIdPort getOrdersByUserIdPort;

    public GetAllOrdersForUserUseCase(GetOrdersByUserIdPort getOrdersByUserIdPort) {
        this.getOrdersByUserIdPort = getOrdersByUserIdPort;
    }

    @Override
    public List<Order> handle(UserId userId) {
        return getOrdersByUserIdPort.byUserId(userId);
    }
}
