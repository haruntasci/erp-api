package com.example.erp.repository;

import com.example.erp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByOrderUuid(UUID uuid);
}
