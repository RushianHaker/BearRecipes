package com.service.bearrecipes.controller.rest;

import com.service.bearrecipes.model.Order;
import com.service.bearrecipes.service.OrderService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/order")
    public List<Order> getAllOrders() {
        return orderService.findAllByUser();
    }

    @GetMapping({"/api/order/{orderId}"})
    public Order infoPageOrder(@PathVariable("orderId") long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping("/api/order")
    public Order addOrder(@RequestBody @NotNull Order order) {
        return orderService.saveUserOrder(order);
    }
}
