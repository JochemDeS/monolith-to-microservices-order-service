package com.example.oderservice.infrastructure.http.order;

import com.example.oderservice.application.order.CreateOrder;
import com.example.oderservice.application.order.CreateOrderUseCase;
import com.example.oderservice.application.order.GetAllOrdersForUserUseCase;
import com.example.oderservice.application.order.GetOrderByIdUseCase;
import com.example.oderservice.domain.OrderId;
import com.example.oderservice.domain.OrderItem;
import com.example.oderservice.domain.User;
import com.example.oderservice.infrastructure.http.product.ProductReadModel;
import com.example.oderservice.infrastructure.http.user.DetailedUserModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order", description = "All endpoints for orders.")
public class OrderController {
    private final GetAllOrdersForUserUseCase getAllOrdersForUserUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(GetAllOrdersForUserUseCase getAllOrdersForUserUseCase,
                           GetOrderByIdUseCase getOrderByIdUseCase,
                           CreateOrderUseCase createOrderUseCase) {
        this.getAllOrdersForUserUseCase = getAllOrdersForUserUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.createOrderUseCase = createOrderUseCase;
    }

    @GetMapping
    public OrderResponseModel getOrders(@AuthenticationPrincipal User user) {
        final var orders = getAllOrdersForUserUseCase.handle(user.id());

        return OrderResponseModel.builder()
                .user(DetailedUserModel.builder()
                        .id(user.id()
                                .value()
                                .intValue())
                        .username(user.username())
                        .email(user.email())
                        .firstName(user.name().firstName())
                        .lastName(user.name().lastName())
                        .build())
                .orders(orders.stream()
                        .map(order -> OrderReadModel.builder()
                                .id(order.id().value())
                                .items(buildOrderItemReadModels(order.items()))
                                .totalAmount(order.getTotalAmount())
                                .build())
                        .toList())
                .build();
    }

    @GetMapping("/{id}")
    public OrderReadModel getOrder(@PathVariable String id) {
        final var orderId = OrderId.builder()
                .value(Long.parseLong(id))
                .build();

        final var order = getOrderByIdUseCase.handle(orderId);

        return OrderReadModel.builder()
                .id(order.id().value())
                .items(buildOrderItemReadModels(order.items()))
                .totalAmount(order.getTotalAmount())
                .build();
    }

    @PostMapping
    public OrderReadModel createOrder(@AuthenticationPrincipal User user) {
        final var request = CreateOrder.builder()
                .userId(user.id())
                .token(user.token())
                .build();

        final var order = createOrderUseCase.handle(request);

        return OrderReadModel.builder()
                .id(order.id().value())
                .totalAmount(order.getTotalAmount())
                .items(buildOrderItemReadModels(order.items()))
                .build();
    }

    private List<OrderItemReadModel> buildOrderItemReadModels(List<OrderItem> items) {
        return items.stream()
                .map(this::buildOrderItemReadModel)
                .toList();
    }

    private OrderItemReadModel buildOrderItemReadModel(OrderItem item) {
        final var product = item.product();

        return OrderItemReadModel.builder()
                .product(ProductReadModel.builder()
                        .id(product.id().value())
                        .title(product.title())
                        .description(product.description())
                        .price(product.price())
                        .brand(product.brand())
                        .category(product.category())
                        .thumbnail(product.thumbnail())
                        .build())
                .quantity(item.quantity())
                .build();
    }
}
