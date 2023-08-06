package com.example.erp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class KeyValue {
    @Column
    @Id
    private String key;
    @Column
    private Double value;
}
