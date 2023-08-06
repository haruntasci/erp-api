package com.example.erp.model;

import jakarta.persistence.*;
import lombok.Data;

@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "product_uuid"
        )
)
@Entity
@Data
@Table(name = "products")
public class Product extends Base {

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private boolean isKDVApplied = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "key", name = "key_value_id")
    private KeyValue KDV;

}