package com.example.erp.repository;

import com.example.erp.model.Product;
import com.example.erp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findStockByProduct(Product product);
}
