package com.example.erp.repository;

import com.example.erp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByFirstNameIgnoreCase(String name);

    Customer findCustomerByUuid(UUID uuid);

    @Modifying
    @Query("DELETE FROM Customer  c WHERE c.uuid = ?1")
    int deleteByUuid(UUID uuid);
}
