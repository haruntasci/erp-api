package com.example.erp.repository;

import com.example.erp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Query("DELETE FROM OrderItem p WHERE p.uuid = ?1")
    int deleteByUuid(UUID uuid);
}
