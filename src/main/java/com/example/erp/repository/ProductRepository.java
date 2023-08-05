package com.example.erp.repository;

import com.example.erp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByNameIgnoreCase(String name);

    Product findProductByUuid(UUID uuid);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.uuid = ?1")
    int deleteByUuid(UUID uuid);
}
