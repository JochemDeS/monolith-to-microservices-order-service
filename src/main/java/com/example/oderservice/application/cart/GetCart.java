package com.example.oderservice.application.cart;

import com.example.oderservice.domain.Cart;
import com.example.oderservice.domain.UserId;

public interface GetCart {
    Cart byUserId(UserId userId, String token);
}
