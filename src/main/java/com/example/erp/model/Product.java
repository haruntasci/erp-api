package com.example.erp.model;

import com.example.erp.enums.KDVType;
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

    @Column
    private KDVType kdvType = KDVType.GENERAL_KDV;

}