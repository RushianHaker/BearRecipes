package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Receipt;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @EntityGraph(attributePaths = {"author", "country"})
    Optional<Receipt> findById(long receiptId);

    @EntityGraph(attributePaths = {"author", "country"})
    List<Receipt> findAll();
}
