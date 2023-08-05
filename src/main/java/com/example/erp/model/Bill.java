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

    @Column
    @ManyToOne
    private Order order;

    @Column
    private long billNumber;//invoice olarak değiştir

    @Column
    private double totalAmount;

}
