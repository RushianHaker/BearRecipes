package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Order;
import com.service.bearrecipes.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user", "stock"})
    Optional<Order> findById(long orderId);

    @EntityGraph(attributePaths = {"user", "stock"})
    List<Order> findAllByUser(@NotNull User user);
}
