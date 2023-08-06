package com.example.erp.model;

import jakarta.persistence.*;
import lombok.Data;

@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "stock_uuid"
        )
)
@Entity
@Data
@Table(name = "stocks")
public class Stock extends Base {


    @OneToOne
    private Product product;

    @Column
    private int quantity;

}
