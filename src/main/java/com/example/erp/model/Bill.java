package com.example.erp.model;

import jakarta.persistence.*;
import lombok.Data;

@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "bill_uuid"
        )
)
@Entity
@Data
@Table(name = "bills")
public class Bill extends Base {


    @ManyToOne
    private Order order;

    @Column
    private double rawAmount;

    @Column
    private double kdvAmount;

    @Column
    private double totalAmount;

}
