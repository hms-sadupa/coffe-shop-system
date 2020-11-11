package com.coffee.shop.service;

import com.coffee.shop.dto.OrderApiRequest;
import com.coffee.shop.model.CoffeeOrder;

public interface OrderService {
    CoffeeOrder createOrder(OrderApiRequest orderApiRequest);

    void cancelOrder(Long oderId);

    CoffeeOrder getCoffeeOrder(Long orderId);

    CoffeeOrder updateOrder(Long orderId, OrderApiRequest orderApiRequest);
}
