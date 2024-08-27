package com.example.oderservice.application.product;

import com.example.oderservice.domain.Product;
import com.example.oderservice.domain.ProductId;

import java.util.List;

public interface GetProduct {
    List<Product> byIdsIn(List<ProductId> ids);
}
