package com.example.erp.repository;

import com.example.erp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUuid(UUID uuid);
    @Modifying
    @Query("DELETE FROM Order p WHERE p.uuid = ?1")
    int deleteByUuid(UUID uuid);
}
