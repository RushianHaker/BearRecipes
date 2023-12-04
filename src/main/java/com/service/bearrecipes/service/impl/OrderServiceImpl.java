package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.OrderRepository;
import com.service.bearrecipes.dao.StockRepository;
import com.service.bearrecipes.dao.UserRepository;
import com.service.bearrecipes.exception.OrderServiceException;
import com.service.bearrecipes.model.Order;
import com.service.bearrecipes.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, StockRepository stockRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order findById(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderServiceException("Order with id: " + orderId + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAllByUser() {
        var user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new OrderServiceException("Can't find user!"));

        return orderRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public Order saveUserOrder(Order order) {
        var user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new OrderServiceException("Can't find user for order: №" + order.getNumber()));

        var stock = stockRepository.findByStockAddressCity(order.getStock().getStockAddressCity())
                .orElseThrow(() -> new OrderServiceException("Can't find stock with address city: " + order.getStock().getStockAddressCity()));

        order.setUser(user);
        order.setStock(stock);

        log.info("E-mail with order №" + order.getNumber() + " was send on stock address!");

        return orderRepository.save(order);
    }
}
