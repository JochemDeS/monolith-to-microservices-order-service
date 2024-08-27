package com.example.oderservice.application.order;

import com.example.oderservice.application.cart.GetCart;
import com.example.oderservice.application.common.UseCase;
import com.example.oderservice.domain.Cart;
import com.example.oderservice.domain.Order;
import com.example.oderservice.domain.OrderItem;
import com.example.oderservice.domain.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateOrderUseCase implements UseCase<CreateOrder, Order> {
    private final SaveOrderPort saveOrderPort;
    private final GetCart getCart;

    public CreateOrderUseCase(SaveOrderPort saveOrderPort, GetCart getCart) {
        this.saveOrderPort = saveOrderPort;
        this.getCart = getCart;
    }

    @Override
    public Order handle(CreateOrder createOrder) {
        var cart = getCart.byUserId(createOrder.userId(), createOrder.token());

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        var order = createAndSaveInitialOrder(createOrder.userId());
        var orderItems = createOrderItems(cart, order);

        return saveOrderWithItems(order, createOrder.userId(), orderItems);
    }

    private Order createAndSaveInitialOrder(UserId userId) {
        Order order = Order.builder()
                .items(List.of())
                .build();
        return saveOrderPort.save(order, userId);
    }

    private List<OrderItem> createOrderItems(Cart cart, Order order) {
        return cart.getItems().stream()
                .map(item -> OrderItem.builder()
                        .orderId(order.id())
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private Order saveOrderWithItems(Order order, UserId userId, List<OrderItem> items) {
        order = Order.builder()
                .id(order.id().value())
                .items(items)
                .build();

        return saveOrderPort.save(order, userId);
    }
}
