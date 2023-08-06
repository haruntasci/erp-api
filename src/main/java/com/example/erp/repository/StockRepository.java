package com.example.erp.repository;

import com.example.erp.model.Product;
import com.example.erp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findStockByProduct(Product product);

    Stock findByUuid(UUID uuid);

    @Modifying
    @Query("DELETE FROM Stock p WHERE p.uuid = ?1")
    int deleteByUuid(UUID uuid);
}
