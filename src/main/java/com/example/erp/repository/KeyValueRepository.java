package com.example.erp.repository;

import com.example.erp.model.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyValueRepository extends JpaRepository<KeyValue, String> {
}
