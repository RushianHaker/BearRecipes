package com.service.bearrecipes.service;


import com.service.bearrecipes.model.Order;

import java.util.List;

public interface OrderService {
    Order findById(long orderId);

    List<Order> findAllByUser();

    Order saveUserOrder(Order order);
}
